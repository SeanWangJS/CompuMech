package com.haswalk.solver.fvm2d.config.boundary;

public class PMLBoundary extends TransmitBoundary{

	private int layerNum;
	private double[] dist;
	private int[] PMLNodesIds;
	private double delta;
	
	public void setDist(double[] dist) {
		this.dist = dist;
	}
	public void setPMLNodesIds(int[] pMLNodesIds) {
		this.PMLNodesIds = pMLNodesIds;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public int getLayerNum() {
		return layerNum;
	}
	public double[] getDist() {
		return dist;
	}
	public int[] getPMLNodesIds() {
		return PMLNodesIds;
	}
	public double getDelta() {
		return delta;
	}
	
	public String toString() {
		return new StringBuilder()
				.append(super.toString())
				.append("layer number: " + layerNum + "\n")
				.toString();
	}
	
}
