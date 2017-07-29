package com.haswalk.solver.fvm1d.config.support.part;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.Part;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;

public class BoundaryPosition implements SetMethod, ToString{

	public final static String BC_ID = "bcId";
	public final static String POINT = "point";
	
	private Part part;
	@Serialize
	private int bcId;
	@Serialize
	private double[] point;
	
	public BoundaryPosition(Part part){
		this.part = part;
	}
	
	public void setBcId(int bcId) {
		this.bcId = bcId;
	}
	
	public void setPoint(double[] point) {
		this.point = point;
	}
	
	public BoundaryPosition set(String property, Object value) {
		setProperty(this, property, value);
		return this;
	}
	
	public Part build() {
		return part;
	}
	
	@Override
	public String toString() {
		return asString();
	}
}
