package com.haswalk.solver.fvm1d.config;

import java.util.HashMap;

import com.haswalk.solver.fvm1d.config.support.ExpLoadBC;
import com.haswalk.solver.fvm1d.config.support.FileLoadBC;
import com.haswalk.solver.fvm1d.config.support.PiecewiseLoadBC;
import com.haswalk.solver.fvm1d.util.SetMethod;

public abstract class Boundary implements SetMethod{

	public final static String FILE_LOAD_BC = "fileLoadBC";
	public final static String EXP_LOAD_BC = "expLoadBC";
	public final static String PIECEWISE_LOAD_BC = "piecewiseLoadBC";
	public final static String SYMMETRIC_BC = "symmetricBC";
	public final static String PML_BC = "pmlBC";
	
	private final static HashMap<String, Boundary> boundaryType = new HashMap<>(); 
	
	static{
		boundaryType.put(FILE_LOAD_BC, new FileLoadBC());
		boundaryType.put(PIECEWISE_LOAD_BC, new PiecewiseLoadBC());
		boundaryType.put(EXP_LOAD_BC, new ExpLoadBC());
	}
	
	private int id;
	private Config1DBuilder builder;
	
	public static Boundary create(int id, String type, Config1DBuilder builder){
		return boundaryType.get(type).setId(id).setBuilder(builder);
	}
	
	public Boundary set(String property, Object value){
		setProperty(this, property, value);
		return this;
	}

	public abstract void init();
	
	public Boundary setId(int id) {
		this.id = id;
		return this;
	}
	
	public Boundary setBuilder(Config1DBuilder builder){
		this.builder = builder;
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
