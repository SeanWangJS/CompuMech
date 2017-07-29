package com.haswalk.solver.fvm1d.config.support.boundarycondition;


import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.BoundaryCondition;

public class FileLoadBC extends BoundaryCondition{

	public static final String FILE_URI = "fileUri";
	
	@Serialize
	private String uri;

	public BoundaryCondition setUri(String uri){
		this.uri = uri;
		return this;
	}

	@Override
	public void init() {
		
	}
	
	
}
