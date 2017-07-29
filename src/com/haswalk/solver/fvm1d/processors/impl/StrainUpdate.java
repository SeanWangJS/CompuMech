package com.haswalk.solver.fvm1d.processors.impl;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.indexing.NDArrayIndex;

import com.haswalk.solver.fvm1d.processors.Processor;

public class StrainUpdate implements Processor{

	private INDArray lens;
	
	private INDArray disp;
	private INDArray elemLength;
	private INDArray strain;
	
	
	@Override
	public void run() {
		lens = disp.get(NDArrayIndex.interval(1, disp.size(1)))
				.sub(disp.get(NDArrayIndex.interval(0, disp.size(1)-1)));
		lens.div(elemLength, strain);
	}

	
	
}
