package com.haswalk.solver.fvm2d.config.part;

import java.util.List;

import com.sean.wang.utils.FileIO;
import com.sean.wang.utils.mesh.MeshProcessor;

public class Mesh {
	
	private String uri;
	
	private List<double[]> vertices;
	private List<int[]> elements;
	private List<List<Integer>> nodesE;
	private List<List<Integer>> nodesN;
	
	private int realNON;
	private int realNOE;
	
	public String toString() {
		return "mesh path: \n"+ uri +"\nend";
	}
	
	public void init() {
		vertices = FileIO.StandartFormat.readVertices(uri);
		elements = FileIO.StandartFormat.readElements(uri);
		MeshProcessor mp = new MeshProcessor(vertices, elements);
		mp.handle();
		nodesE = mp.getSurrE();
		nodesN = mp.getSurrN();
		this.realNOE = vertices.size();
		this.realNOE = elements.size();
		
	}
	
	
	
	public void initWith(List<double[]> vertices, List<int[]> elements){
		this.vertices = vertices;
		this.elements = elements;
		MeshProcessor mp = new MeshProcessor(vertices, elements);
		mp.handle();
		nodesE = mp.getSurrE();
		nodesN = mp.getSurrN();
	}
	
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

	public int getNON(){
		return vertices.size();
	}
	public int getNOE(){
		return elements.size(); 
	}
	
	public int getRealNON() {
		return realNON;
	}
	public int getRealNOE() {
		return realNOE;
	}
}
