package com.haswalk.solver.fvm1d.config.support;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Boundary;

public class PiecewiseLoadBC extends Boundary{

	public final static String TIMES = "times";
	public final static String VALUES = "values";
	
	@Serialize
	private double[] times;
	@Serialize
	private double[] values;
	
	@Override
	public void init() {
		
	}
	
	public Boundary setTimes(double[] times) {
		this.times = times;
		return this;
	}
	public Boundary setValues(double[] values) {
		this.values = values;
		return this;
	}
}
