package com.haswalk.solver.fvm1d.config;

public class Material {

	public final static String ELASTIC_MODULE = "elasticModule";
	public final static String DENSITY = "density";
	
	private int id;
	private Config1DBuilder builder;
	
	private double elasticModule;
	private double density;

	public Material(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder;
	}
	
	public Material set(String property, double value){
		switch(property){
		case ELASTIC_MODULE:
			elasticModule = value;
			break;
		case DENSITY:
			density = value;
			break;
		default:
			System.err.println("Error: can not match property " + property + " in material");
			break;
		}
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public Config1DBuilder build() {
		builder.putMaterial(this);
		return builder;
	}
	
	public String toString() {
		return new StringBuilder()
				.append("{'elasticModule': " + elasticModule + ", 'density': " +density+ "}")
				.toString();
	}
}
