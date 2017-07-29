package com.haswalk.solver.fvm2d.config.boundary;

import java.util.function.Function;

public class Load {
	
	protected Function<Double, Double> function;
	
	public void init(){}
	
	public Function<Double, Double> getFunction() {
		return function;
	}
}
