package com.haswalk.solver.fvm2d.processors.support.force;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Component;
import com.sean.wang.utils.ArrUtil;

@Component
public class InternalForceUpdate2 {

	private List<double[]> vertices;
	private List<int[]> elements;
	private List<List<Integer>> nodesE;
	private List<Integer> boundNodesId;
	private double[] forceX, forceY, sx, sy, sxy;
	
	public InternalForceUpdate2(List<double[]> vertices, List<int[]> elements, List<List<Integer>> nodesE,
			List<Integer> boundNodesId, double[] forceX, double[] forceY, double[] sx, double[] sy, double[] sxy) {
		this.vertices = vertices;
		this.elements = elements;
		this.nodesE = nodesE;
		this.boundNodesId = boundNodesId;
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
			boolean isBoundNode = boundNodesId.contains(i);
			if(isBoundNode) {
				int eid = ean.get(0);
				int[] e = elements.get(eid);
				int nk = ArrUtil.findNext(e, i);
				double[] p1 = vertices.get(i);
				double[] p2 = vertices.get(nk);
				fx += fx(sx[eid], sxy[eid], p1, p2);
				fy += fy(sxy[eid], sy[eid], p1, p2);
			}
			for (int j = 0; j < ean.size(); j++) {
				int eid = ean.get(j);
				int[] e = elements.get(eid);
				int ni = ArrUtil.findNext(e, i);
				int nj = ArrUtil.findPre(e, i);
				double[] p1 = vertices.get(ni);
				double[] p2 = vertices.get(nj);
				fx += fx(sx[eid], sxy[eid], p1, p2);
				fy += fy(sxy[eid], sy[eid], p1, p2);
			}
			if(isBoundNode) {
				int eid = ean.get(ean.size() - 1);
				int[] e = elements.get(eid);
				int nk = ArrUtil.findPre(e, i);
				double[] p1 = vertices.get(nk);
				double[] p2 = vertices.get(i);
				fx += fx(sx[eid], sxy[eid], p1, p2);
				fy += fy(sxy[eid], sy[eid], p1, p2);
			}
			forceX[i] = fx;
			forceY[i] = fy;
		}

	}

	private final double fx(double sx, double sxy, double[] p1, double[] p2) {
		return (sx * (p2[1] - p1[1]) - sxy * (p2[0] - p1[0])) / 2.0;
	}
	private final double fy(double sxy, double sy, double[] p1, double[] p2) {
		return (sxy * (p2[1] - p1[1]) - sy * (p2[0] - p1[0])) / 2.0;
	}
}
