package com.haswalk.solver.fvm1d.processors.impl;

import org.nd4j.linalg.api.ndarray.INDArray;

import com.haswalk.solver.fvm1d.processors.Processor;

public class StressUpdate implements Processor{

	private double E;
	private INDArray strain;
	private INDArray stress;
	
	@Override
	public void run() {
		strain.mul(E, stress);
	}

}
