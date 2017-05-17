package com.haswalk.solver.fvm1d.config.support;


import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Boundary;

public class FileLoadBC extends Boundary{

	public static final String FILE_URI = "fileUri";
	
	@Serialize
	private String uri;

	public Boundary setUri(String uri){
		this.uri = uri;
		return this;
	}

	@Override
	public void init() {
		
	}
	
	
}
