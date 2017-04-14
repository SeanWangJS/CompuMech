package com.haswalk.solver.fvm2d.processors.support;

import java.util.List;

import com.haswalk.solver.fvm2d.util.Geom;


public class SinglePartTimestepUpdate {
	private double c;
	private List<double[]> vertices;
	private List<int[]> elements;
	private double[] eArea;
	private double[] charLen;
	
	private int NOE;
	
	public SinglePartTimestepUpdate(double c, List<double[]> vertices, List<int[]> elements, double[] eArea, double[] charLen) {
		super();
		this.charLen = charLen;
		this.c = c;
		this.vertices = vertices;
		this.elements = elements;
		this.eArea = eArea;
		NOE = elements.size();
	}

	public double calc() {
		double minLen = Double.MAX_VALUE;
		
		for(int i = 0; i < NOE; i++) {
			double d1 = Geom.distSq(vertices.get(elements.get(i)[0]), vertices.get(elements.get(i)[2]));
			double d2 = Geom.distSq(vertices.get(elements.get(i)[1]), vertices.get(elements.get(i)[3]));
			charLen[i] = eArea[i] / Math.sqrt(((d1 > d2) ? d1 : d2));
			if(minLen > charLen[i]) {
				minLen = charLen[i];
			}
		}
		return minLen / c;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("single part time step update\n")
		.append("sound speed c: " + c +"\n")
		.append("vertices num: " + vertices.size() +"\n")
		.append("elements num: " + elements.size() + "\n")
		.append("elem area size: " + eArea.length + "\n")
		.append("end\n");
		return builder.toString();
	}
}
