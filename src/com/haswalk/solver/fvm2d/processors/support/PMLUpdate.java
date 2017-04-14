package com.haswalk.solver.fvm2d.processors.support;

import com.haswalk.solver.fvm2d.processors.Processor;

public class PMLUpdate implements Processor{

	private double delta;
	private double R = 1e-9;
	private double[] vx;
	private double[] vy;
	private double[] ax;
	private double[] ay;
	private int[] PMLNode;
	private double[] dist;
	private double cp;
	
	public PMLUpdate(double delta, double[] vx, double[] vy, double[] ax, double[] ay, int[] pMLNode, double[] dist, double cp) {
		this.delta = delta;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
		this.PMLNode = pMLNode;
		this.dist = dist;
		this.cp = cp;
	}

	@Override
	public void calc() {
		int NOPMLN = PMLNode.length;
		double damp = Double.MIN_VALUE;
		for(int i = 0; i < NOPMLN; i++) {
			int nid = PMLNode[i];
			double d = dist[i];
			double dampX = cp * 3 * vx[i] / (2 * delta) * Math.log10(1 / R) * Math.pow(d / delta, 2);
			double dampY = cp * 3 * vy[i] / (2 * delta) * Math.log10(1 / R) * Math.pow(d / delta, 2);
			
			ax[nid] += dampX;
			ay[nid] += dampY;
			if(Math.abs(dampX) > Math.abs(damp)){
				damp = dampX;
			}
			if(Math.abs(dampY) > Math.abs(damp)) {
				damp = dampY;
			}
		}
//		System.out.println(damp);
	}
	
}













