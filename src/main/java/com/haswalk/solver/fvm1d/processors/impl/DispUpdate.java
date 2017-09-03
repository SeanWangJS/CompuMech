package com.haswalk.solver.fvm1d.processors.impl;

import com.chauncey.DblArr;

import com.haswalk.solver.fvm1d.components.TimeControl;
import com.haswalk.solver.fvm1d.processors.Processor;

public class DispUpdate implements Processor{

	private DblArr vertices;
	private DblArr disp;
	private DblArr vel;
	private TimeControl time;
	
	@Override
	public void calc() {
		DblArr d = vel.mul(time.step);
		disp.add_(d);
		vertices.add_(d);
	}

}
