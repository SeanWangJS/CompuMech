package com.haswalk.solver.fvm1d.util;

import java.util.ArrayList;
import java.util.List;

public class MeshProcessor {

	private List<Double> vertices;
	private List<int[]> elements;
	private List<List<Integer>> nodesE;
	
	public MeshProcessor(List<Double> vertices, List<int[]> elements) {
		this.vertices = vertices;
		this.elements = elements;
		nodesE = new ArrayList<>();
		for(int i = 0, len = vertices.size(); i < len; i++) {
			nodesE.add(new ArrayList<>());
		}
	}
	
	public void handle() {
		for(int i = 0, len = elements.size(); i < len; i++) {
			int[] elem = elements.get(i);
			nodesE.get(elem[0]).add(i);
			nodesE.get(elem[1]).add(i);
		}
		
		for(List<Integer> ean : nodesE) {
			if(ean.size() == 1) {
				continue;
			}
			double ec1 = centerOf(ean.get(0));
			double ec2 = centerOf(ean.get(1));
			if(ec1 > ec2) {
				
			}
		}
		
	}
	
	private double centerOf(int eid) {
		int[] elem = elements.get(eid);
		return (vertices.get(elem[0]) + vertices.get(elem[1])) / 2.0;
	}
	
}
