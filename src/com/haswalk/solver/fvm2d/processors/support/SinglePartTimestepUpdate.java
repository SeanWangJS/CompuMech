package com.haswalk.solver.fvm2d.processors.support;

import java.util.List;

import com.sean.wang.utils.ArrUtil;
import com.sean.wang.utils.Geom;

public class SinglePartTimestepUpdate {
	private double c;
	private List<double[]> vertices;
	private List<int[]> elements;
	private double[] eArea;
	
	public SinglePartTimestepUpdate(double c, List<double[]> vertices, List<int[]> elements, double[] eArea) {
		super();
		this.c = c;
		this.vertices = vertices;
		this.elements = elements;
		this.eArea = eArea;
	}

	public double calc() {
		int NOE = elements.size();
		double[] data = new double[NOE];
		for(int i = 0; i < NOE; i++) {
			double d1 = Geom.dist(vertices.get(elements.get(i)[0]), vertices.get(elements.get(i)[2]));
			double d2 = Geom.dist(vertices.get(elements.get(i)[1]), vertices.get(elements.get(i)[3]));
			data[i] = eArea[i] / ((d1 > d2) ? d1 : d2);
		}
		return ArrUtil.min(data) / c;
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
