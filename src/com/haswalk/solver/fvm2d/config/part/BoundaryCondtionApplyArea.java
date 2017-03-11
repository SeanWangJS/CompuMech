package com.haswalk.solver.fvm2d.config.part;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.sean.wang.utils.Geom;

public class BoundaryCondtionApplyArea {
	
	private HashMap<Integer, double[][]> idLineMap;
	
	private HashMap<Integer, List<Integer>> applyNodesIdMap;
	
	public HashMap<Integer, double[][]> getIdLineMap() {
		return idLineMap;
	}

	public void init(List<double[]> vertices){
		applyNodesIdMap = new HashMap<>();
		idLineMap.forEach((bid, line) -> {
			applyNodesIdMap.put(bid, Geom.onLineWithinSort(vertices, line[0], line[1], 1e-8));
		});
	}
	
	public List<Integer> getBcIds(){
		List<Integer> bcids = new ArrayList<>();
		idLineMap.forEach((id, line) -> bcids.add(id));
		return bcids;
	}
	
	public HashMap<Integer, List<Integer>> getApplyNodesIdMap() {
		return applyNodesIdMap;
	}
	
	public List<Integer> getApplyNodesId(int bid) {
		return applyNodesIdMap.get(bid);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("boundary conditions apply area: \n");
		idLineMap.forEach((id, line) -> {
			builder.append(id + ": " + Arrays.toString(line[0]) + "--" + Arrays.toString(line[1]) + "\n");
		});
		builder.append("end");
		return builder.toString();
	}
}
