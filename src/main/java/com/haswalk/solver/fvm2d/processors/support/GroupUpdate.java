package com.haswalk.solver.fvm2d.processors.support;

import static com.haswalk.solver.fvm2d.components.FieldData.*;
import static com.haswalk.solver.fvm2d.components.ModelData.*;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.support.group.AreaUpdate;
import com.haswalk.solver.fvm2d.processors.support.group.DensityUpdate;
import com.haswalk.solver.fvm2d.processors.support.group.NodeMassUpdate;

public class GroupUpdate implements Processor{
	
	private AreaUpdate au;
	private DensityUpdate du;
	private NodeMassUpdate nmu;
	
	@Override
	public void calc() {
		au.calc();
		du.calc();
		nmu.calc();
	}
	
	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md){
		au = new AreaUpdate((List<int[]>) md.get(ELEMENTS), (List<double[]>) md.get(VERTICES), 
				fd.get(ELEM_AREA), fd.get(ELEM_AREA_LST));
		du = new DensityUpdate(fd.get(ELEM_DENSITY), fd.get(ELEM_MASS), fd.get(ELEM_AREA));
		nmu = new NodeMassUpdate((List<List<Integer>>)md.get(ELEMS_AROUND_NODE), 
				(List<int[]>)md.get(ELEMENTS), (List<double[]>)md.get(VERTICES), 
				fd.get(NODE_MASS), fd.get(ELEM_DENSITY));
	}

}
