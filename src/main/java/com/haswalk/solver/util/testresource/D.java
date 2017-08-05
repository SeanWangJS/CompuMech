package com.haswalk.solver.util.testresource;

import com.haswalk.solver.util.Serialize;

public class D {

	@Serialize
	private int p1;
	@Serialize
	private String p2;
	@Serialize
	private double p3;
	@Serialize 
	private boolean p4;
	@Serialize 
	private String p5;
	@Serialize
	private D2 p6;
	
	public String toString(){
		return p1+", " + p2+", " + p3+", " + p4 + ", " + p5  + p6.toString();
	}

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public double getP3() {
		return p3;
	}

	public void setP3(double p3) {
		this.p3 = p3;
	}

	public boolean isP4() {
		return p4;
	}

	public void setP4(boolean p4) {
		this.p4 = p4;
	}
	public void setP5(String p5) {
		this.p5 = p5;
	}
	public void setP6(D2 p6) {
		this.p6 = p6;
	} 
}
