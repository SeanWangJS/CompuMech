package com.haswalk.solver.fvm2d.config.boundary;

public class StressLoadBoundary extends LoadBoundary{
	
	public String toString() {
		return new StringBuilder()
		    .append("type: " + type + "\n")
			.append(super.toString()+"\n")
			.toString();
	}
	
}
