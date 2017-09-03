package com.haswalk.solver.fvm1d.processors.impl;

import com.chauncey.DblArr;

import com.haswalk.solver.fvm1d.components.TimeControl;
import com.haswalk.solver.fvm1d.processors.Processor;

public class VelUpdate implements Processor{

	private DblArr acc;
	private DblArr vel;
	private TimeControl time;
	
	@Override
	public void calc() {
		vel.add_(acc.mul(time.step));
	}

}
