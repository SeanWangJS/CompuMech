package com.haswalk.solver.fvm2d.config.boundary;

public class CForceLoadBoundary extends LoadBoundary{
	private double angle;
	
	public double getAngle(){
		return angle;
	}
	
	public String toString() {
		return new StringBuilder().append("type: " + type+ "\n")
				.append("angle: " + angle+ "\n")
				.append(super.toString() + "\n")
				.toString();
	}
}
