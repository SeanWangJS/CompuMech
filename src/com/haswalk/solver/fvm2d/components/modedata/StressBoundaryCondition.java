package com.haswalk.solver.fvm2d.components.modedata;

import java.util.List;
import java.util.function.Function;

public class StressBoundaryCondition extends BoundaryCondition{
	private Function<Double, Double> load;
	private List<Integer> applyNodesId;
	public StressBoundaryCondition(String type, Function<Double, Double> load, List<Integer> applyNodesId) {
		super(type);
		this.load = load;
		this.applyNodesId = applyNodesId;
	}
	public Function<Double, Double> getLoad() {
		return load;
	}
	public List<Integer> getApplyNodesId() {
		return applyNodesId;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("type: " + type + "\n")
			   .append("apply nodes id: " + applyNodesId.toString() + "\n");
		return builder.toString();
	}
	
}
