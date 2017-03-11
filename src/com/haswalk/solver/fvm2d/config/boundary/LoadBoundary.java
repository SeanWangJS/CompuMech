package com.haswalk.solver.fvm2d.config.boundary;

import java.util.HashMap;
import java.util.function.Function;

import com.haswalk.solver.fvm2d.config.Boundary;

public class LoadBoundary extends Boundary{
	public final static String FILE_LOAD = "file";
	public final static String EXP_LOAD = "expression";
	public final static String PIECEWISE_LOAD = "piecewise";
	public final static HashMap<String, Class<?>> loadMap = new HashMap<>();
	static{
		loadMap.put(FILE_LOAD, FileLoad.class);
		loadMap.put(EXP_LOAD, ExpLoad.class);
		loadMap.put(PIECEWISE_LOAD, PiecewiseLoad.class);
	}
	protected Load load;
	
	protected String loadType;
	
	public void setLoad(Load load){
		this.load = load;
	}
	
	public String getLoadType(){
		return loadType;
	}
	
	public Function<Double, Double> getLoad(){
		return load.getFunction();
	}
	
	public String toString(){
		return new StringBuilder()
				.append("load type: " + loadType + "\n")
			    .append("load: \n" + load.toString() + "end")
			    .toString();
	}
	
	public void init(){
		load.init();
	}
}
