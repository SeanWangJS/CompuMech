package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.function.Function;

import com.haswalk.solver.fvm2d.components.TimeControl;

public abstract class LoadBoundaryApply {
	
	protected double[] forceX;
	protected double[] forceY;
	protected TimeControl time;
	protected Function<Double, Double> load;

	public abstract void calc();
}
