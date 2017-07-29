package com.haswalk.solver.fvm1d.config;

import java.util.HashMap;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.support.boundarycondition.ExpLoadBC;
import com.haswalk.solver.fvm1d.config.support.boundarycondition.FileLoadBC;
import com.haswalk.solver.fvm1d.config.support.boundarycondition.PiecewiseLoadBC;
import com.haswalk.solver.fvm1d.util.SetMethod;

public abstract class BoundaryCondition implements SetMethod{

	public final static String FILE_LOAD_BC = "fileLoadBC";
	public final static String EXP_LOAD_BC = "expLoadBC";
	public final static String PIECEWISE_LOAD_BC = "piecewiseLoadBC";
	public final static String SYMMETRIC_BC = "symmetricBC";
	public final static String PML_BC = "pmlBC";
	
	public final static String ID = "id";
	
	private final static HashMap<String, BoundaryCondition> boundaryType = new HashMap<>(); 
	
	static{
		boundaryType.put(FILE_LOAD_BC, new FileLoadBC());
		boundaryType.put(PIECEWISE_LOAD_BC, new PiecewiseLoadBC());
		boundaryType.put(EXP_LOAD_BC, new ExpLoadBC());
	}
	
	@Serialize
	protected int id;
	protected Config1DBuilder builder;
	
	public static BoundaryCondition create(int id, String type, Config1DBuilder builder){
		return boundaryType.get(type).setId(id).setBuilder(builder);
	}
	
	public BoundaryCondition set(String property, Object value){
		setProperty(this, property, value);
		return this;
	}

	public abstract void init();
	
	public BoundaryCondition setId(int id) {
		this.id = id;
		return this;
	}
	
	public BoundaryCondition setBuilder(Config1DBuilder builder){
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
