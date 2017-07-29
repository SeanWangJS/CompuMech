package com.haswalk.solver.fvm1d.processors.impl;

import org.nd4j.linalg.api.ndarray.INDArray;

import com.haswalk.solver.fvm1d.processors.Processor;

public class AccUpdate implements Processor{

	private INDArray nMass;
	private INDArray force;
	private INDArray acc;
	@Override
	public void run() {
		force.div(nMass, acc);
	}
	
}
