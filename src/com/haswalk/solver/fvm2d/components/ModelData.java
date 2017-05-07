package com.haswalk.solver.fvm2d.components;

import java.util.HashMap;
import java.util.List;

public class ModelData {

	public final static String VERTICES = "vertices";
	public final static String ELEMENTS = "elements";
	public final static String ELEMS_AROUND_NODE = "elemsAroundNode";
	public final static String NODES_AROUDN_NODE = "nodesAroundNode";
	public final static String GAUGE_NODES_ID = "gaugeNodesId";
	public final static String BOUND_NODES_ID = "boundNodesId";
	public final static String BOUNDARY_CONDITION = "boundaryCondition";
	
	private HashMap<String, List<?>> modelData = new HashMap<>();
	
	public List<?> get(String dataName) {
		return modelData.get(dataName);
	}
	
	public ModelData put(String dataName, List<?> data){
		modelData.put(dataName, data);
		return this;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("ModelData-------------------------------\n");
		builder.append("vertices num: " + modelData.get(VERTICES).size() + "\n")
			   .append("elements num: " + modelData.get(ELEMENTS).size() + "\n")
			   .append("nodes around elem num: " + modelData.get(ELEMS_AROUND_NODE).size() + "\n")
			   .append("nodes around node num: " + modelData.get(NODES_AROUDN_NODE).size() + "\n")
			   .append("boundary conditions: " + "\n");
		
		modelData.get(BOUNDARY_CONDITION).forEach(b -> builder.append(b.toString() + "\n"));
		builder.append("ModelData End---------------------------\n");
		return builder.toString();
	}
}
