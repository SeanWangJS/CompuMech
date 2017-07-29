# CompuMech
## 简介
>“物体受到冲击作用会产生变形，进而在内部形成冲击波向外扩散，并在边界上反射，这是极为普遍的现象。整个过程可以使用应力波传播方程来描述，但是方程的求解在理论上有很大的困难，因为本来有解析解的偏微分方程就很少，再加上实际中复杂的计算域以及边界条件等等问题。使得计算数学这一数学分支在力学研究中处于极端重要的地位。”

基于有限体积法的动力学计算程序，目前具备二维、单物体、线弹性强度模型的计算能力，能够施加集中力、分布力边界条件以及对称边界条件。网格类型可以是四边形单元、三角形单元或者混合型（其中三角形网格能有效解决集中冲击荷载造成的沙漏变形效应，从而在无限大介质点源力冲击作用下能得到与Pilant W.L推导的理论解相差无几的数值解）。

## 程序结构
<!--![](https://github.com/SeanWangJS/compu_mech/raw/develop/resource/arch.PNG)-->

<img src="https://github.com/SeanWangJS/compu_mech/raw/develop/resource/arch.PNG" height="80%" width="80%" />

上图为程序的基本结构，最上层的SolverBuilder接受一个json格式的配置文件，然后构造求解器Solver类
```
Solver solver = new FVM2DSolverBuilder().parse(config).create();
```

在SolverBuilder具体实现的内部定义了json解析方案，这里主要利用Gson库进行反序列化，然后形成Config类，包含材料、边界条件、初始条件、计算对象、控制方式、输出方案等等自定义信息。但是这时得到的内容还不够完善，例如材料的json表达
```
"materials":{
	  "1":{
	    "elasticModule": 3.727E10,
	    "poissonRatio": 0.24,
	    "density": 2490,
	    "dampingRatio": 0,
	    "naturalFrequency": 0,
	    "strengthModelType": "elastic"
	   }
}
```
上面对于线弹性材料的描述已足够充分，但是却没有显式指定p波波速、体积模量、剪切模量等信息，而这些内容可以由已知量运算得到。类似的还有很多，因此Config类在生成之后还需要再初始化一次以得到必要的信息。

Processors标签下的内容阐述了核心算法的迭代过程，其中每一个子过程都是一个运算类，依赖于前一过程的计算结果，并且又要推进下一过程的执行，它们之中的一些共享了部分计算中间数据，例如各种单元量（应变率、应力等），节点量（节点力、节点质量等）。这些数据的初始化又依赖于Config内的信息，因此在Config和Processors之间定义了中间数据Components，使用这些数据帮助运算类的生成，可以简化运算类的生成逻辑，使得程序的层次结构更加清晰。
