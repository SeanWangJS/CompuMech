package com.haswalk.solver.fvm1d.config.support;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Boundary;

public class ExpLoadBC extends Boundary{

	public final static String EXPRESSION = "expression";
	
	@Serialize
	private String exp;
	
	@Override
	public void init() {
		
	}

	public Boundary setExp(String exp) {
		this.exp = exp;
		return this;
	}
}
