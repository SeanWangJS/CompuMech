package com.haswalk.solver.fvm2d.processors.extend;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.FieldData;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.processors.Processor;

public class NodePressureUpdate implements Processor{

	private double[] ePressure;
	private double[] nPressure;
	private List<List<Integer>> nodesE;
	
	@Override
	public void calc() {
		int NON = nPressure.length;
		for(int i = 0; i < NON; i++) {
			List<Integer> ean = nodesE.get(i);
			double temp = 0;
			for(int eid: ean) {
				temp += ePressure[eid];
			}
			nPressure[i] = temp / (double)ean.size();
		} 
	}

	@SuppressWarnings("unchecked")
	@Injection
	public void set(FieldData fd, ModelData md) {
		ePressure = fd.get(FieldData.ELEM_PRESSURE);
		nPressure = fd.get("node_pressure");
		nodesE = (List<List<Integer>>) md.get(ModelData.ELEMS_AROUND_NODE);
		
	}
	
}
