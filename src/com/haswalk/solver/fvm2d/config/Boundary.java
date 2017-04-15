package com.haswalk.solver.fvm2d.config;

import java.util.HashMap;

import com.haswalk.solver.fvm2d.config.boundary.CForceLoadBoundary;
import com.haswalk.solver.fvm2d.config.boundary.PMLBoundary;
import com.haswalk.solver.fvm2d.config.boundary.StressLoadBoundary;
import com.haswalk.solver.fvm2d.config.boundary.SymmetricBoundary;
import com.haswalk.solver.fvm2d.util.Initiation;

public class Boundary implements Initiation{

	public final static String FORCE = "force";
	public final static String STRESS = "stress";
	public final static String SYMMETRIC = "symmetric";
	public final static String PERFECT_MATCHED_LAYER = "PML";
	
	public final static HashMap<String, Class<?>> typeMap = new HashMap<>();
	static{
		typeMap.put(FORCE, CForceLoadBoundary.class);
		typeMap.put(STRESS, StressLoadBoundary.class);
		typeMap.put(SYMMETRIC, SymmetricBoundary.class);
		typeMap.put(PERFECT_MATCHED_LAYER, PMLBoundary.class);
	}
	
	protected String type;
	
	public String getType(){
		return type;
	}
	
	public void init(){}
	
//	public Function<Double, Double> getLoad(){
//		return null;
//	}
}
