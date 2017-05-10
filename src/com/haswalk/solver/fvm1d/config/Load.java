package com.haswalk.solver.fvm1d.config;

public class Load {

	private int id;
	private Config1DBuilder builder;
	
	public Load(int id, Config1DBuilder builder){
		this.id = id;
		this.builder = builder;
	}
	
	public Load set(String property, String value) {
		return this;
	}
	
	public Load set(String property, double[] value) {
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public Config1DBuilder build() {
		builder.putLoad(this);
		return builder;
	}
	
}
