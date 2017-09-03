package com.haswalk.solver.fvm1d.processors.impl;

import com.chauncey.DblArr;
import com.haswalk.solver.fvm1d.processors.Processor;

public class AccUpdate implements Processor{

	private DblArr nMass;
	private DblArr force;
	private DblArr acc;
	@Override
	public void calc() {
		force.div(nMass, acc);
	}
	
}
