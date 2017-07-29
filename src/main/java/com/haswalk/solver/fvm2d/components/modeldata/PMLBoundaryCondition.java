package com.haswalk.solver.fvm2d.components.modeldata;

import java.util.Arrays;
import java.util.List;

public class PMLBoundaryCondition extends BoundaryCondition{

	private List<Integer> applyNodesId;
	private double[] dist;
	private int[] PMLNodesId;
	private int[] PMLBoundNodesID;
	private double delta;
	
	public PMLBoundaryCondition(String type, List<Integer> applyNodesId, double[] dist, int[] PMLNodesId, int[] PMLBoundNodesID, double delta) {
		super(type);
		this.applyNodesId = applyNodesId;
		this.dist = dist;
		this.PMLNodesId = PMLNodesId;
		this.PMLBoundNodesID = PMLBoundNodesID;
		this.delta = delta;
	}
	public List<Integer> getApplyNodesId() {
		return applyNodesId;
	}
	
	public double[] getDist() {
		return dist;
	}
	public int[] getPMLNodesId() {
		return PMLNodesId;
	}
	public int[] getPMLBoundNodesID() {
		return PMLBoundNodesID;
	}
	public double getDelta() {
		return delta;
	}
	public String toString() {
		return new StringBuilder()
				.append("type: " + type + "\n")
				.append("apply nodes id: " + applyNodesId.toString() + "\n")
				.append("pml nodes id: " + Arrays.toString(PMLNodesId) + "\n")
				.append("distance: " + Arrays.toString(dist) + "\n")
				.append("delta: " + delta + "\n")
				.toString();
	}

}
