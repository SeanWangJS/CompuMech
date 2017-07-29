package com.haswalk.solver.fvm1d.config.support.part;

import java.util.List;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Part;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;

public class Mesh implements SetMethod, ToString{

	public final static String URI = "uri";
	
	private Part part;
	
	public Mesh(Part part) {
		this.part = part;
	}

	@Serialize
	private String uri;
	
	public Mesh set(String property, Object value) {
		setProperty(this, property, value);
		return this;
	}
	
	public Mesh setUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
	public Part build() {
		return part;
	}
	
	public List<int[]> elements;
	public List<Double> vertices;
	
	public void init() {
		
	}
	
}
