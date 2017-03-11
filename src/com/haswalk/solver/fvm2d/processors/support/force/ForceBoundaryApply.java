package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.function.Function;

import com.haswalk.solver.fvm2d.components.TimeControl;

public class ForceBoundaryApply extends LoadBoundaryApply{
	
	private int applyNodeId;
	private double angle;
	
	public ForceBoundaryApply(double[] forceX, double[] forceY, TimeControl time, Function<Double, Double> load,
			int applyNodeId, double angle) {
		super();
		this.forceX = forceX;
		this.forceY = forceY;
		this.time = time;
		this.load = load;
		this.applyNodeId = applyNodeId;
		this.angle = angle;
	}

	public void calc() {
		double value = load.apply(time.getCurrentTime());
		forceX[applyNodeId] += value * Math.cos(angle / 180.0);
		forceY[applyNodeId] += value * Math.sin(angle / 180.0);
	}
}
