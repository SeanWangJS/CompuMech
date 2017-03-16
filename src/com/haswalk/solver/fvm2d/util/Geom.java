package com.haswalk.solver.fvm2d.util;

public class Geom {
	public final static double distSq(double[] p1, double[] p2) {
		return Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2);
	}

	public static double dist(double[] p1, double[] p2) {
		return Math.sqrt(distSq(p1, p2));
	}
}
