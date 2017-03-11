package com.haswalk.solver.fvm2d.components;

import java.util.HashMap;

public class MaterialProperty {
	
	public final static String STRENGTH_MODEL = "strengthModel";
	public final static String ELASTIC_MODULE = "elasticModule";
	public final static String POISSON_RATIO = "poissonRatio";
	public final static String DENSITY = "density";
	public final static String DAMPING_RATIO = "dampingRatio";
	public final static String NATRUAL_FREQUENCY = "naturalFrequency";
	public final static String BULK_MODULE = "bulkModule";
	public final static String SHEAR_MODULE = "shearModule";
	public final static String SOUND_SPEED = "soundSpeed";
	
	private HashMap<String, Double> propertyData = new HashMap<>();
	
	public void set(String name, double value){
		propertyData.put(name, value);
	}
	
	public Object get(String name){
		return propertyData.get(name);
	}
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("MaterialProperty-------------------------------\n");
		propertyData.forEach((name, value) -> {
			builder.append(name + ": " + value + "\n");
		});
		builder.append("ModelData End----------------------------\n");
		return builder.toString();
	}
}
