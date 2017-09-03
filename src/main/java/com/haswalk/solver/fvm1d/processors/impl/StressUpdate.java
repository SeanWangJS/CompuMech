package com.haswalk.solver.fvm1d.processors.impl;

import com.chauncey.DblArr;

import com.haswalk.solver.fvm1d.processors.Processor;

public class StressUpdate implements Processor{

	private double E;
	private DblArr strain;
	private DblArr stress;
	
	@Override
	public void calc() {
		strain.mul(E, stress);
	}

}
