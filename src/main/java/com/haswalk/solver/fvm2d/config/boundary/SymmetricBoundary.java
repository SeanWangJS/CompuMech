package com.haswalk.solver.fvm2d.config.boundary;

import com.haswalk.solver.fvm2d.config.Boundary;

public class SymmetricBoundary extends Boundary{
	private String symmetric;
	public String getSymmetric(){
		return symmetric;
	}
	public String toString(){
		return new StringBuilder().append("type: " + type + "\n")
			.append("symmetric: " + symmetric + "\n")
			.toString();
	}
}
