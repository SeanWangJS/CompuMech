package com.haswalk.solver.fvm2d.util;

public class Geom {
	public final static double distSq(double[] p1, double[] p2) {
		return Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2);
	}

	public static double dist(double[] p1, double[] p2) {
		return Math.sqrt(distSq(p1, p2));
	}
	
	public static double[] center(double[][] polygon) {
		int len = polygon[0].length;
		double[] c = new double[len];
		for (int i = 0, plen = polygon.length; i < plen; i++) {
			for (int j = 0; j < c.length; j++) {
				c[j] += polygon[i][j] / (double) plen;
			}
		}
		return c;
	}
	
	public static double pole_theta(double px, double py) {
		double theta;
		if(px == 0) {
			if(py > 0) { 
				theta = Math.PI / 2.0;
			}else{
				theta = 1.5 * Math.PI;
			}
		}else{
			double tanTheta = py / px;
			if(py >= 0) {
				if(tanTheta >= 0) {
					theta = Math.atan(tanTheta);
				}else{
					theta = Math.atan(tanTheta) + Math.PI;
				}
			}else{
				if(tanTheta >= 0) {
					theta = Math.atan(tanTheta) + Math.PI;
				}else{
					theta = Math.atan(tanTheta) + 2 * Math.PI;
				}
				
			}
		}
		return theta;
	}
}
