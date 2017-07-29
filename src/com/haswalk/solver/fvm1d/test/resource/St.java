package com.haswalk.solver.fvm1d.test.resource;

import java.util.ArrayList;
import java.util.List;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.util.ToString;

public class St implements ToString{

	@Serialize
	private Integer i = 1;
	@Serialize
	private double d = 11.1;
	@Serialize
	private String a = "cscd";
	@Serialize
	private List<A> aa = new ArrayList<>();
	
	public St() {
		aa.add(new A("sdfs"));
		aa.add(new A("scd"));
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
