package com.haswalk.solver.fvm1d.test.resource;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.util.ToString;

public class A implements ToString{
	@Serialize
	private String b;
	
	public A(String b) {
		this.b = b;
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
}
