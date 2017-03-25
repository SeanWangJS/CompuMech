package com.haswalk.solver.fvm2d.config.boundary;

import java.util.Arrays;

import com.haswalk.solver.fvm2d.config.Boundary;

public class TransmitBoundary extends Boundary{

	protected double[] outNorm;
	
	public double[] getOutNorm() {
		return outNorm;
	}
	
	public String toString() {
		return new StringBuilder()
				.append("type: " + type + "\n")
				.append("out norm: " + Arrays.toString(outNorm)+"\n")
				.toString();
	}
	
}
