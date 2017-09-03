package com.haswalk.solver.fvm2d.config.part;

import com.haswalk.hasutil.IO;
import com.haswalk.hasutil.obj.tuple;
import com.haswalk.solver.fvm2d.util.MeshProcessor;

import java.util.List;

public class Mesh {
	
	private String uri;
	private String[] uri2;
	
	private List<double[]> vertices;
	private List<int[]> elements;
	private List<List<Integer>> nodesE;
	private List<List<Integer>> nodesN;
//	private List<Integer> boundNodesId; 
	
	public String toString() {
		
		return "mesh path: \n"+ uri +"\nend";
	}
	
	public void init() {
//		if(uri == null) {
//			uri = System.getProperty(uri2[0]) + uri2[1];
//		}
		uri = System.getProperty("user.dir") + "/src/resources/mesh.txt";
		System.out.println("Read mesh from: " + uri);
		tuple<List<double[]>, List<int[]>> t = IO.stdMesh2d.load(uri);
		vertices = t.fst;
		elements = t.sec;
		MeshProcessor mp = new MeshProcessor(vertices, elements);
		mp.handle();
		nodesE = mp.getSurrE();
		nodesN = mp.getSurrN();
//		this.realNOE = vertices.size();
//		this.realNOE = elements.size();
//		boundSearch();
	}
//	private void boundSearch() {
//		boundNodesId = new ArrayList<>();
//		for(int i = 0, len = nodesE.size(); i < len; i++) {
//			List<Integer> ean = nodesE.get(i);
//			int eid1 = ean.get(0);
//			int eidL = ean.get(ean.size() - 1);
//			int[] e1 = elements.get(eid1);
//			int[] eL = elements.get(eidL);
//			int sec = ArrUtil.findNext(e1, i);
//			int e = ArrUtil.findPre(eL, i);
//			if(sec != e) {
//				boundNodesId.add(i);
//			}
//		}
////		FileIO.writeDoubleArrList(LsUtil.select(vertices, boundNodesId), "E:/fvm/7/boundNode.txt","\t");
////		System.out.println("++++++++++++++++++bound nodes size = " + boundNodesId.size());
//	}
	
	public String getWorkspace(){
		String[] str = uri.split("/");
		return uri.replaceAll("/" +  str[str.length - 1], "");
		
	}
	
	public List<double[]> getVertices(){
		return vertices;
	}
	public List<int[]> getElements() {
		return elements;
	}
	
	public List<List<Integer>> getNodesE() {
		return nodesE;
	}

	public List<List<Integer>> getNodesN() {
		return nodesN;
	}
	
	public void setNodesN(List<List<Integer>> nodesN) {
		this.nodesN = nodesN;
	}
	public void setNodesE(List<List<Integer>> nodesE) {
		this.nodesE = nodesE;
	}

//	public List<Integer> getBoundNodesId() {
//		return boundNodesId;
//	} 
	
	public int getNON(){
		return vertices.size();
	}
	public int getNOE(){
		return elements.size(); 
	}
}
