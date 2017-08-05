package com.haswalk.solver.util.testresource;


import com.haswalk.solver.util.Serialize;

public class D2 {

	@Serialize
	private int id;
	@Serialize
	private D3 p3;
	
	public String toString() {
		return "d2 id = " + id + ", " + p3.toString();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setP3(D3 p3) {
		this.p3 = p3;
	}
}
