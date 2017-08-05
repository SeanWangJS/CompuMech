package com.haswalk.solver.fvm1d.config.support.boundarycondition;

import com.haswalk.solver.fvm1d.config.BoundaryCondition;
import com.haswalk.solver.fvm1d.util.ToString;
import com.haswalk.solver.util.Serialize;

public class PiecewiseLoadBC extends BoundaryCondition implements ToString{

	public final static String TIMES = "times";
	public final static String VALUES = "values";
	
	@Serialize
	private double[] times;
	@Serialize
	private double[] values;
	
	@Override
	public void init() {
		
	}
	
	public BoundaryCondition setTimes(double[] times) {
		this.times = times;
		return this;
	}
	public BoundaryCondition setValues(double[] values) {
		this.values = values;
		return this;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
