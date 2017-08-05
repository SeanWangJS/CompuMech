package com.haswalk.solver.fvm1d.config;

import java.util.ArrayList;
import java.util.List;

import com.haswalk.solver.fvm1d.config.support.part.BoundaryPosition;
import com.haswalk.solver.fvm1d.config.support.part.Mesh;
import com.haswalk.solver.fvm1d.util.SetMethod;
import com.haswalk.solver.fvm1d.util.ToString;
import com.haswalk.solver.util.Serialize;

public class Part implements SetMethod, ToString{

	public final static String MATERIAL_ID = "materialID";
	public final static String OUTPUT_ID = "outputID";
	public final static String GAUGE_POINTS = "gaugePoints";
	
	@Serialize
	private int id;
	private Config1DBuilder builder;

	@Serialize
	private int materialID;
	@Serialize
	private int outputID;
	@Serialize
	private double[][] gaugePoints;
	@Serialize
	private List<BoundaryPosition> boundaryPositions = new ArrayList<>();
	@Serialize
	private Mesh mesh;
	
	public Part(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder; 
	}
	
	public Part set(String property, Object value) {
		setProperty(this, property, value);
		return this;
	}
	
	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	
	public void setOutputID(int outputID) {
		this.outputID = outputID;
	}
	
	public void setGaugePoints(double[][] gaugePoints) {
		this.gaugePoints = gaugePoints;
	}
	
	public int getId() {
		return id;
	}
	
	public BoundaryPosition boundaryPosition() {
		BoundaryPosition bp = new BoundaryPosition(this);
		boundaryPositions.add(bp);
		return bp;
	}
	public Mesh mesh() {
		mesh = new Mesh(this);
		return mesh;
	}
	
	public Config1DBuilder build() {
		builder.putPart(this);
		return builder;
	}
	
	public void init() {}
	
	@Override
	public String toString() {
		return asString();
	}

}
