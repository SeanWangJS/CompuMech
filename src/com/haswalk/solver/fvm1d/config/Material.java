package com.haswalk.solver.fvm1d.config;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.util.SetMethod;

public class Material implements SetMethod{

	public final static String ELASTIC_MODULE = "elasticModule";
	public final static String DENSITY = "density";
	
	private int id;
	private Config1DBuilder builder;
	
	@Serialize
	private double elasticModule;
	@Serialize
	private double density;

	public static Material create(int id, Config1DBuilder builder) {
		return new Material().setId(id).setBuilder(builder);
	}
	
	public Material set(String property, Object value){
		setProperty(this, property, value);
		return this;
	}
	
	public Material setId(int id) {
		this.id = id;
		return this;
	}
	
	public Material setBuilder(Config1DBuilder builder) {
		this.builder = builder;
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
	
	public Material setDensity(double density) {
		this.density = density;
		return this;
	}
	public Material setElasticModule(double elasticModule) {
		this.elasticModule = elasticModule;
		return this;
	}
}
