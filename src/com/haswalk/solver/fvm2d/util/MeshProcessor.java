package com.haswalk.solver.fvm2d.util;

import java.util.ArrayList;
import java.util.List;

import com.sean.utils.ArrUtil;
import com.sean.utils.LsUtil;
import com.sean.utils.Sort;
import com.sean.wang.utils.obj.Pair;

public class MeshProcessor {
	private List<double[]> vertices;
	private List<int[]> elements;
	private int NON;
	private int NOE;
	private List<List<Integer>> nodesE;
	private List<Integer> boundIDs;
	
	public MeshProcessor(List<double[]> vertices, List<int[]> elements){
		this.vertices = vertices;
		this.elements = elements;
		this.NON = vertices.size();
		this.NOE = elements.size();
		nodesE = new ArrayList<>();
		boundIDs = new ArrayList<>();
	}
	
	public void handle(){
		
		List<List<Integer>> elemsWitchContainsNode = new ArrayList<>();
		for(int i = 0; i < NON; i++) {
			elemsWitchContainsNode.add(new ArrayList<>());
		}
		for(int eid = 0; eid < NOE; eid++) {
			int[] elem = elements.get(eid);
			for(int nid: elem) {
				if(!elemsWitchContainsNode.get(nid).contains(eid)) {
					elemsWitchContainsNode.get(nid).add(eid);
				}
			}
		}
		
		double[][] centers = new double[NOE][];
		for(int eid = 0; eid < NOE; eid++) {
			centers[eid] = Geom.center(LsUtil.select(vertices, elements.get(eid)));
		}
		
		for(int nid = 0; nid < NON; nid++) {
			double[] v = vertices.get(nid);
			List<Integer> eids = elemsWitchContainsNode.get(nid);
			List<Pair<Integer, Double>> pairs = new ArrayList<>();
			double[] thetas = new double[eids.size()];
			for(int j = 0; j < eids.size(); j++) {
				int eid = eids.get(j);
				double[] c = centers[eid];
				thetas[j] = Geom.pole_theta(c[0] - v[0], c[1] - v[1]);
				pairs.add(new Pair<>(eid, thetas[j]));
			}
			Sort.quickSort(pairs);
			
			List<Integer> ean = new ArrayList<>();
			for(int j = 0; j < eids.size(); j++) {
				ean.add(pairs.get(j).getOther());
			}
			nodesE.add(ean);
		}
		
		for(int nid = 0; nid < NON; nid++) {
			List<Integer> ean = nodesE.get(nid);
			boolean isBound = false;
			for(int j = 0; j < ean.size(); j++) {
				int eid1 = ean.get(j);
				int eid2 = ean.get((j + 1) % ean.size());
				int n1 = ArrUtil.findPre(elements.get(eid1), nid);
				int n2 = ArrUtil.findNext(elements.get(eid2), nid);
				if(n1 != n2) {
					isBound = true;
					break;
				}
			}
			if(isBound) {
				boundIDs.add(nid);
			}
		} 
		
		for(int nid: boundIDs) {
			List<Integer> ean = nodesE.get(nid);
			while(!continues(ean, nid)){
				int eFst = ean.get(0);
				ean.remove(0);
				ean.add(eFst);
			}
		}
	} 
	
	private boolean continues(List<Integer> ean, int nid){
		for(int i = 0; i < ean.size() - 1; i++) {
			int eid1 = ean.get(i);
			int eid2 = ean.get(i + 1);
			int n1 = ArrUtil.findPre(elements.get(eid1), nid);
			int n2 = ArrUtil.findNext(elements.get(eid2), nid);
			if(n1 != n2) {
				return false;
			}
		}
		return true;
	}
	
	public List<List<Integer>> getSurrE(){
		return nodesE;
	}
	public List<List<Integer>> getSurrN(){
		return null;
	}
	public List<double[]> getVertices(){
		return vertices;
	}
	public List<int[]> getElements(){
		return elements;
	}
	
	public static void main(String[] args){
		double[][] vert = {{0, 0},{1, 0},{2, 0},{0, 1},{1, 1},{2, 1},{0, 2},{1, 2},{2, 2}, {0.5, 3}, {1.5, 3}};
		int[][] elem = {{0, 1, 3, 4},{1, 2, 4, 5},{3, 4, 6, 7},{4, 5, 7, 8}, {9, 7, 6}, {10, 7, 9}, {10, 8, 7}};
		List<double[]> vertices = new ArrayList<>();
		List<int[]> elements = new ArrayList<>();
		for(double[] v:vert){
			vertices.add(v);
		}
		for(int[] e:elem){
			elements.add(e);
		}
		new MeshProcessor(vertices, elements).handle();
	}
}
