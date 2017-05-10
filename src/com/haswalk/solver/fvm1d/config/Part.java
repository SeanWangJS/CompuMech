package com.haswalk.solver.fvm1d.config;

public class Part {

	public final static String MESH_URI = "meshUri";
	public final static String MATERIAL_ID = "materialID";
	public final static String OUTPUT_ID = "outputID";
	
	private int id;
	private Config1DBuilder builder;

	private int materialID;
	private int outputID;
	private Mesh mesh = new Mesh();
	private Boundary boundary = new Boundary();
	private double[][] gauges;
	
	public Part(int id, Config1DBuilder builder) {
		this.id = id;
		this.builder = builder; 
	}
	
	public Part set(String property, String value) {
		switch (property) {
		case MESH_URI:
			mesh.setUri(value);
			break;
		default:
			break;
		}
		return this;
	}
	public Part set(String property, int value) {
		switch (property) {
		case MATERIAL_ID:
			materialID = value;
			break;
		case OUTPUT_ID:
			outputID = value;
			break;
		default:
			System.err.println("Error: can not match property " + property + " in part.");
			break;
		}
		return this;
	}
	
	public Part set(String property1, int value1, String property2, double[] value2){
		boundary.setLoadID(value1);
		boundary.setPosition(value2);
		return this;
	}
	
	public Part set(String property, double[][] value) {
		gauges = value;
		return this;
	}
	
	public int getId() {
		return id;
	}
	
	public Config1DBuilder build() {
		builder.putPart(this);
		return builder;
	}
	
	public void init() {}
	
	public class Mesh{
		private String uri;
		public void setUri(String uri) {
			this.uri = uri;
		}
		public void init(){}
	}
	public class Boundary{
		private int loadID;
		private double[] position;
		public void setLoadID(int loadID) {
			this.loadID = loadID;
		}
		public void setPosition(double[] position) {
			this.position = position;
		}
		public void init() {}
	}
}
