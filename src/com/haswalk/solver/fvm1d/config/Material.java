package com.haswalk.solver.fvm1d.config;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;

public class Material implements SetMethod, ToString{

	public final static String ELASTIC_MODULE = "elasticModule";
	public final static String DENSITY = "density";
	
	@Serialize
	private int id;
	
	private Config1DBuilder builder;
	
	@Serialize
	private double elasticModule;
	@Serialize
	private double density;
	
	public Material(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder;
	}
	
	public Material set(String property, Object value){
		setProperty(this, property, value);
		return this;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public Config1DBuilder build() {
		builder.putMaterial(this);
		return builder;
	}
	
	public String toString() {
		return asString();
	}
	
	public void setDensity(double density) {
		this.density = density;
	}
	public void setElasticModule(double elasticModule) {
		this.elasticModule = elasticModule;
	}
}
