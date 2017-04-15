package com.haswalk.solver.fvm2d.components.modedata;

public abstract class BoundaryCondition {
	protected String type;
	public BoundaryCondition(String type) {
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
