package com.haswalk.solver.fvm1d.processors.impl;

import org.nd4j.linalg.api.ndarray.INDArray;

import com.haswalk.solver.fvm1d.components.TimeControl;
import com.haswalk.solver.fvm1d.processors.Processor;

public class DispUpdate implements Processor{

	private INDArray vertices;
	private INDArray disp;
	private INDArray vel;
	private TimeControl time;
	
	@Override
	public void run() {
		INDArray d = vel.mul(time.step);
		disp.addi(d);
		vertices.addi(d);
	}

}
