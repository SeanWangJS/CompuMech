package com.haswalk.solver.fvm1d.config;

public class Config1DBuilder {

	private Config1D config = new Config1D();
	
	public Material material(int id){
		return new Material(id, this);
	}
	
	public BoundaryCondition boundary(int id, String type){
		return BoundaryCondition.create(id, type, this);
	}
	
	public Part part(int id){
		return new Part(id, this);
	}
	
	public Control control() {
		return new Control(this);
	}
	
	public Output output(int id) {
		return new Output(id, this);
	}
	
	Config1DBuilder putMaterial(Material material) {
		config.putMaterial(material);
		return this;
	}
	
	Config1DBuilder putLoad(BoundaryCondition load) {
		config.putLoad(load);
		return this;
	}
	Config1DBuilder putPart(Part part) {
		config.putPart(part);
		return this;
	}
	Config1DBuilder setControl(Control control) {
		config.setControl(control);
		return this;
	}
	Config1DBuilder putOutput(Output output) {
		config.putOutput(output);
		return this;
	}
	
	public Config1D build() {
		return config;
	}
	
}
