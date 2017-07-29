package com.haswalk.solver.fvm2d.test;//package com.haswalk.solver.fvm2d.test;
//
//import com.haswalk.solver.fvm2d.fvmconfig.FVMConfig;
//import com.haswalk.solver.fvm2d.fvmconfig.FVMConfigBuilder;
//
//public class ConfigBuilderTest {
//
//	public void test1() {
//		FVMConfig config = new FVMConfigBuilder()
//				.material(1, "elasticModel")
//					.set("density", 2490)
//					.set("elasticModule", 3.7e10)
//					.set("possionRatio", 0.3)
//					.build()
//				.material(2, "mohrCoulomb")
//					.set("density", 2490)
//					.set("elasticModule", 3.7e10)
//					.set("possionRatio", 0.3)
//					.build()
//				.boundary(1, "stress")
//					.load("piecewise")
//					.set("timeInterval", new double[]{0, 0.015, 0.02})
//					.set("values", new double[]{0, 1000000, 0})
//					.build()
//				.part(1, "E:/fvm/7/mesh.txt")
//					.set("materialID", 1)
//					.set("boundaryID", 1, "line", new double[][]{{-50, -0.5}, {-50, 0.5}})
//					.set("outputID", 1)
//					.set("gauge", new double[][]{{-30, 0}})
//					.build()
//				.control()
//					.timeControl()
//						.set("endTime", 0.02)
//						.set("factor", 0.667)
//						.build()
//					.build()
//				.output(1)
//					.save()
//						.set("inc", -1)
//						.set("items", "node_stress_x", "node_stress_y")
//						.build()
//					.gauge()
//						.set("inc", 2)
//						.set("items", "node_vel_x", "node_stress_x")
//						.build()
//					.build()
//				.build();
//					
//	}
//	
//}
