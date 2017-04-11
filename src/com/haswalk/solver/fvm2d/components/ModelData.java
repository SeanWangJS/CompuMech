package com.haswalk.solver.fvm2d.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ModelData {

	public final static String VERTICES = "vertices";
	public final static String ELEMENTS = "elements";
	public final static String NODES_AROUND_ELEM = "nodesAroundElem";
	public final static String NODES_AROUDN_NODE = "nodesAroundNode";
	public final static String GAUGE_NODES_ID = "gaugeNodesId";
	public final static String BOUND_NODES_ID = "boundNodesId";
	
	public final static String BOUNDARY_CONDITION = "boundaryCondition";
	
	private HashMap<String, List<?>> modelData = new HashMap<>();
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("ModelData-------------------------------\n");
		builder.append("vertices num: " + modelData.get(VERTICES).size() + "\n")
			   .append("elements num: " + modelData.get(ELEMENTS).size() + "\n")
			   .append("nodes around elem num: " + modelData.get(NODES_AROUND_ELEM).size() + "\n")
			   .append("nodes around node num: " + modelData.get(NODES_AROUDN_NODE).size() + "\n")
			   .append("boundary conditions: " + "\n");
		
		modelData.get(BOUNDARY_CONDITION).forEach(b -> builder.append(b.toString() + "\n"));
		builder.append("ModelData End---------------------------\n");
		return builder.toString();
	}
	
	public List<?> get(String dataName) {
		return modelData.get(dataName);
	}
	
	public ModelData put(String dataName, List<?> data){
		modelData.put(dataName, data);
		return this;
	}

	public abstract class BoundaryCondition{
		protected String type;
		public BoundaryCondition(String type) {
			this.type = type;
		}
		public String getType(){
			return type;
		}
	}
	public class StressBoundaryCondition extends BoundaryCondition{
		private Function<Double, Double> load;
		private List<Integer> applyNodesId;
		public StressBoundaryCondition(String type, Function<Double, Double> load, List<Integer> applyNodesId) {
			super(type);
			this.load = load;
			this.applyNodesId = applyNodesId;
		}
		public Function<Double, Double> getLoad() {
			return load;
		}
		public List<Integer> getApplyNodesId() {
			return applyNodesId;
		}
		
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("type: " + type + "\n")
				   .append("apply nodes id: " + applyNodesId.toString() + "\n");
			return builder.toString();
		}
		
	}
	public class ForceBoundaryCondition extends BoundaryCondition{
		private Function<Double, Double> load;
		private int applyNodeId;
		private double angle;
		public ForceBoundaryCondition(String type, Function<Double, Double> load, int applyNodeId, double angle) {
			super(type);
			this.load = load;
			this.applyNodeId = applyNodeId;
			this.angle = angle;
		}
		public Function<Double, Double> getLoad() {
			return load;
		}
		public int getApplyNodeId() {
			return applyNodeId;
		}
		public double getAngle() {
			return angle;
		} 
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("type: " + type)
				   .append("apply node id: " + applyNodeId + "\n")
				   .append("angle: " + angle + "\n");
			return builder.toString();
		}
	}
	public class PMLBoundaryCondition extends BoundaryCondition{
		private List<Integer> applyNodesId;
		private double[] dist;
		private int[] PMLNodesId;
		private double delta;
		
		public PMLBoundaryCondition(String type, List<Integer> applyNodesId, double[] dist, int[] PMLNodesId, double delta) {
			super(type);
			this.applyNodesId = applyNodesId;
			this.dist = dist;
			this.PMLNodesId = PMLNodesId;
			this.delta = delta;
		}
		public List<Integer> getApplyNodesId() {
			return applyNodesId;
		}
		
		public double[] getDist() {
			return dist;
		}
		public int[] getPMLNodesId() {
			return PMLNodesId;
		}
		public double getDelta() {
			return delta;
		}
		public String toString() {
			return new StringBuilder()
					.append("type: " + type + "\n")
					.append("apply nodes id: " + applyNodesId.toString() + "\n")
					.append("pml nodes id: " + Arrays.toString(PMLNodesId) + "\n")
					.append("distance: " + Arrays.toString(dist) + "\n")
					.append("delta: " + delta + "\n")
					.toString();
		}
	}
	public class SymmetricBoundaryCondition extends BoundaryCondition{
		private List<Integer> applyNodesId;
		private String symmetric;
		
		public SymmetricBoundaryCondition(String type, List<Integer> applyNodesId, String symmetric) {
			super(type);
			this.applyNodesId = applyNodesId;
			this.symmetric = symmetric;
		}
		
		public List<Integer> getApplyNodesId() {
			return applyNodesId;
		}
		
		public String getSymmetric() {
			return symmetric;
		}
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("type: " + type + "\n")
				.append("symmetric: " + symmetric + "\n")
			   .append("apply nodes id: " + applyNodesId.toString() + "\n");
			return builder.toString();
		}
	}
	
}
