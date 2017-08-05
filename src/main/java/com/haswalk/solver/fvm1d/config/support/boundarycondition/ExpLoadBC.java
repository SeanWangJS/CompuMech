package com.haswalk.solver.fvm1d.config.support.boundarycondition;

import com.haswalk.solver.fvm1d.config.BoundaryCondition;
import com.haswalk.solver.util.Serialize;

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
