package com.haswalk.solver.fvm2d.processors;

public interface Processor {

	public final static String TIMESTEP_UPDATE = "TimestepUpdate"; 
	public final static String FORCE_UPDATE = "ForceUpdate";
	public final static String ACC_UPDATE = "AccUpdate";
	public final static String VEL_UPDATE = "VelUpdate";
	public final static String DISP_UPDATE = "DispUpdate";
	
	public final static String GROUP_UPDATE = "GroupUpdate";
	
	public final static String STRAIN_UPDATE = "StrainUpdate";
	public final static String STRESS_DEV_UPDATE = "StressDevUpdate";
	public final static String PRESSURE_UPDATE = "pressureUpdate";
	public final static String STRESS_UPDATE = "StressUpdate";
	
	public void calc();
	
}
