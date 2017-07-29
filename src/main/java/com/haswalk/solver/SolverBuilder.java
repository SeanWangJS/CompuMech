package com.haswalk.solver;

public interface SolverBuilder {
	
	public SolverBuilder parseConfig(String config);
	
	public Solver create();
	
}
