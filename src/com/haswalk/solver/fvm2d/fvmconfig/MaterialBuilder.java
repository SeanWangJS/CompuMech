package com.haswalk.solver.fvm2d.fvmconfig;

import com.haswalk.solver.fvm2d.config.Material;

public class MaterialBuilder {

	private FVMConfigBuilder builder;
	private Material material;
	
	public MaterialBuilder(int id, String strengthModel, FVMConfigBuilder builder) {
		this.builder = builder;
	}
	
	public MaterialBuilder(String property, double value) {
		switch(property) {
		case "s": 
		}
	}
	
	public FVMConfigBuilder build() {
		return builder;
	}
	
}
