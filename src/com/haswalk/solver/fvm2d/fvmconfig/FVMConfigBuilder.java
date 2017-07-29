package com.haswalk.solver.fvm2d.fvmconfig;

public class FVMConfigBuilder {

	public MaterialBuilder material(int id, String strengthModel) {
		return new MaterialBuilder(id, strengthModel, this);
	}
	
}
