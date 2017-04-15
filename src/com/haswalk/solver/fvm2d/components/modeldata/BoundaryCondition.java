package com.haswalk.solver.fvm2d.components.modeldata;

public abstract class BoundaryCondition {
	protected String type;
	public BoundaryCondition(String type) {
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
