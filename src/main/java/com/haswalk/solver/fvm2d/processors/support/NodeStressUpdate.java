package com.haswalk.solver.fvm2d.processors.support;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.processors.Processor;

public class NodeStressUpdate implements Processor{

	private double[] esx;
	private double[] esy;
	private double[] esxy;
	private double[] nsx;
	private double[] nsy;
	private double[] nsxy;
	private List<List<Integer>> nodesE;
	
	@Override
	public void calc() {
		
		int NON = nodesE.size();
		for(int i = 0; i < NON; i++) {
			List<Integer> ean = nodesE.get(i);
			double[] temp = new double[3];
			ean.forEach(eid -> {
				temp[0] += esx[eid];
				temp[1] += esy[eid];
				temp[2] += esxy[eid];
			});
			nsx[i] = temp[0] / (double) ean.size();
			nsy[i] = temp[1] / (double) ean.size();
			nsxy[i] = temp[2] / (double) ean.size();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md){
		esx = fd.get(FieldData.ELEM_STRESS_X);
		esy = fd.get(FieldData.ELEM_STRESS_Y);
		esxy = fd.get(FieldData.ELEM_STRESS_XY);
		nsx = fd.get("node_stress_x");
		nsy = fd.get("node_stress_y");
		nsxy = fd.get("node_stress_xy");
		nodesE = (List<List<Integer>>) md.get(ModelData.ELEMS_AROUND_NODE);
	}
	
}

