package com.haswalk.solver.fvm2d.config.boundary;

import java.util.Arrays;

import com.haswalk.solver.fvm2d.config.Boundary;

public class TransmitBoundary extends Boundary{

	private String method;
	private double[] outNorm;
	
	public String toString() {
		return new StringBuilder()
				.append("type: " + type + "\n")
				.append("method: " + method +"\n")
				.append("out norm: " + Arrays.toString(outNorm))
				.toString();
	}
	
}
