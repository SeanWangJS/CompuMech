package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.List;
import java.util.function.Function;

import com.haswalk.solver.fvm2d.components.TimeControl;

public class StressBoundaryApply extends LoadBoundaryApply{
	
	private List<double[]> vertices;
	private List<Integer> applyNodesId;
	
	public StressBoundaryApply(double[] forceX, double[] forceY, List<double[]> vertices, TimeControl time,
			Function<Double, Double> load, List<Integer> applyNodesId) {
		this.forceX = forceX;
		this.forceY = forceY;
		this.vertices = vertices;
		this.time = time;
		this.load = load;
		this.applyNodesId = applyNodesId;
	}

	public void calc(){
		double value = load.apply(time.getCurrentTime());
		for(int j = 0, len = applyNodesId.size(); j < len - 1; j++) {
			int n1id = applyNodesId.get(j);
			int n2id = applyNodesId.get(j + 1);
			double[] p1 = vertices.get(n1id);
			double[] p2 = vertices.get(n2id);
			double fx = - value * (p2[1] - p1[1]) / 2.0;
			double fy = value * (p2[0] - p1[0]) / 2.0;
			forceX[n1id] += fx;
			forceY[n1id] += fy;
			forceX[n2id] += fx;
			forceY[n2id] += fy;
		}
	}
}
