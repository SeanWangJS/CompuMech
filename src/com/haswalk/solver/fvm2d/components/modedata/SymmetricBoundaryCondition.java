package com.haswalk.solver.fvm2d.components.modedata;

import java.util.List;

public class SymmetricBoundaryCondition extends BoundaryCondition{
	private List<Integer> applyNodesId;
	private String symmetric;
	
	public SymmetricBoundaryCondition(String type, List<Integer> applyNodesId, String symmetric) {
		super(type);
		this.applyNodesId = applyNodesId;
		this.symmetric = symmetric;
	}
	
	public List<Integer> getApplyNodesId() {
		return applyNodesId;
	}
	
	public String getSymmetric() {
		return symmetric;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("type: " + type + "\n")
			.append("symmetric: " + symmetric + "\n")
		   .append("apply nodes id: " + applyNodesId.toString() + "\n");
		return builder.toString();
	}
}
