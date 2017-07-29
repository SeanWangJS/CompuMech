package com.haswalk.solver.fvm2d.config;

import com.haswalk.solver.fvm2d.config.part.BoundaryConditionApplyPosition;
import com.haswalk.solver.fvm2d.config.part.Gauge;
import com.haswalk.solver.fvm2d.config.part.Mesh;
import com.haswalk.solver.fvm2d.util.Initiation;

public class Part implements Initiation{

	private int materialID;
	private int outputID;
	private Mesh mesh;
	private BoundaryConditionApplyPosition boundaryCondition;
	private Gauge gauge;
	
	public void init(){
		mesh.init();
		boundaryCondition.init(mesh.getVertices());
		gauge.init(mesh.getVertices());
	}
	
	public int getMaterialID() {
		return materialID;
	}
	
	public int getOutputID(){
		return outputID;
	}
	
	public Mesh getMesh() {
		return mesh;
	}

	public BoundaryConditionApplyPosition getBoundaryCondition() {
		return boundaryCondition;
	}

	public Gauge getGauge() {
		return gauge;
	}

	public String toString(){
		return new StringBuilder()
				.append("material id:" + materialID)
				.append("\n")
				.append(mesh.toString())
				.append("\n")
				.append(boundaryCondition.toString())
				.append("\n")
				.append(gauge.toString())
				.toString();
	}
}
