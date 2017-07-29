package com.haswalk.solver.fvm1d.config.support.boundarycondition;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.BoundaryCondition;

public class ExpLoadBC extends BoundaryCondition{

	public final static String EXPRESSION = "expression";
	
	@Serialize
	private String exp;
	
	@Override
	public void init() {
		
	}

	public BoundaryCondition setExp(String exp) {
		this.exp = exp;
		return this;
	}
}
