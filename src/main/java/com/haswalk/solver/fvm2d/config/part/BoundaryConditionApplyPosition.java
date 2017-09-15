package com.haswalk.solver.fvm2d.config.part;

import com.haswalk.hasutil.Geom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoundaryConditionApplyPosition {
	
	private HashMap<Integer, double[][]> idLineMap;
	private HashMap<Integer, double[]> idCirMap;
	private HashMap<Integer, List<Integer>> applyNodesIdMap;
	private double tolerance = 1e-5;

	public void init(List<double[]> vertices){
		applyNodesIdMap = new HashMap<>();
		parseIdLineMap(vertices);
		parseIdCirMap(vertices);
	}
	private void parseIdCirMap(List<double[]> vertices) {
		if(idCirMap == null) {
			return;
		}
		idCirMap.forEach((bid, cir) -> {
			List<Integer> idOnCir = Geom.onCircleWithinSort(vertices, new double[]{cir[0], cir[1]}, cir[2], tolerance);
			idOnCir.add(idOnCir.get(0));
			applyNodesIdMap.put(bid, idOnCir);
			
//			FileIO.writeIntegerList(idOnCir, "E:/fvm/22/hole.txt");
		});
		
	}
	private void parseIdLineMap(List<double[]> vertices) {
		if(idLineMap == null) {
			return;
		}
		idLineMap.forEach((bid, line) -> {
			applyNodesIdMap.put(bid, Geom.onLineWithinSort(vertices, line[0], line[1], tolerance));
		});
	}
	
	public List<Integer> getBcIds(){
		List<Integer> bcids = new ArrayList<>();
		applyNodesIdMap.forEach((id, line) -> bcids.add(id));
		return bcids;
	}
	
	public List<Integer> getApplyNodesId(int bid) {
		return applyNodesIdMap.get(bid);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("boundary conditions apply nodes id: \n");
		applyNodesIdMap.forEach((id, nodesID) -> {
			builder.append(id).append(": ").append(nodesID).append("\n");
			builder.append("size: ").append(nodesID.size()).append("\n");
		});
		builder.append("end");
		return builder.toString();
	}
}
