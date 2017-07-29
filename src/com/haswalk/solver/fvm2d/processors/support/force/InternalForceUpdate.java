package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.List;

import com.sean.utils.ArrUtil;

public class InternalForceUpdate {

	private List<double[]> vertices;
	private List<int[]> elements;
	private List<List<Integer>> nodesE;
	private double[] forceX, forceY, sx, sy, sxy;

	public InternalForceUpdate(List<double[]> vertices, List<int[]> elements, List<List<Integer>> nodesE,
			double[] forceX, double[] forceY, double[] sx, double[] sy, double[] sxy) {
		this.vertices = vertices;
		this.elements = elements;
		this.nodesE = nodesE;
		this.forceX = forceX;
		this.forceY = forceY;
		this.sx = sx;
		this.sy = sy;
		this.sxy = sxy;
	}

	public void calc() {
		int NON = vertices.size();
		
		for (int i = 0; i < NON; i++) {
			double fx = 0;
			double fy = 0;
			List<Integer> ean = nodesE.get(i);
			for (int j = 0; j < ean.size(); j++) {
				int eid = ean.get(j);
				int[] e = elements.get(eid);
				int ni = ArrUtil.findNext(e, i);
				int nj = ArrUtil.findPre(e, i);

				double[] p1 = vertices.get(ni);
				double[] p2 = vertices.get(nj);
				fx += (sx[eid] * (p2[1] - p1[1]) - sxy[eid] * (p2[0] - p1[0])) / 2.0;
				fy += (sxy[eid] * (p2[1] - p1[1]) - sy[eid] * (p2[0] - p1[0])) / 2.0;
			}
			forceX[i] = fx;
			forceY[i] = fy;
		}

	}

}
