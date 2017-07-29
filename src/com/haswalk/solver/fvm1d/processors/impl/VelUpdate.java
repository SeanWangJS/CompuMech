package com.haswalk.solver.fvm1d.processors.impl;

import org.nd4j.linalg.api.ndarray.INDArray;

import com.haswalk.solver.fvm1d.components.TimeControl;
import com.haswalk.solver.fvm1d.processors.Processor;

public class VelUpdate implements Processor{

	private INDArray acc;
	private INDArray vel;
	
	private TimeControl time;
	
	@Override
	public void run() {
		vel.add(acc.mul(time.step), vel);
	}

}
