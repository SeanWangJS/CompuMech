package com.haswalk.solver.fvm1d.test.resource;

import com.haswalk.solver.fvm1d.util.ToString;
import com.haswalk.solver.util.Serialize;

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
