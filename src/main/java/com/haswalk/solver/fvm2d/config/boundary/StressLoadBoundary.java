package com.haswalk.solver.fvm2d.config.boundary;

public class StressLoadBoundary extends LoadBoundary{
	
	public String toString() {
		return ("type: " + type + "\n") +
				super.toString() + "\n";
	}
	
}
