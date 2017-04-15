package com.haswalk.solver.fvm2d.components.modeldata;

import java.util.function.Function;

public class ForceBoundaryCondition extends BoundaryCondition{
	private Function<Double, Double> load;
	private int applyNodeId;
	private double angle;
	public ForceBoundaryCondition(String type, Function<Double, Double> load, int applyNodeId, double angle) {
		super(type);
		this.load = load;
		this.applyNodeId = applyNodeId;
		this.angle = angle;
	}
	public Function<Double, Double> getLoad() {
		return load;
	}
	public int getApplyNodeId() {
		return applyNodeId;
	}
	public double getAngle() {
		return angle;
	} 
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("type: " + type)
			   .append("apply node id: " + applyNodeId + "\n")
			   .append("angle: " + angle + "\n");
		return builder.toString();
	}
}
