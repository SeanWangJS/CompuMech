package com.haswalk.solver.fvm1d.processors.impl;

import com.chauncey.DblArr;


import com.haswalk.solver.fvm1d.processors.Processor;

public class StrainUpdate implements Processor{

	private DblArr lens;
	private DblArr disp;
	private DblArr elemLength;
	private DblArr strain;
	
	@Override
	public void calc() {
	    lens = disp.subarray(1, disp.size());
		lens.div(elemLength, strain);
	}

	
	
}
