package com.haswalk.solver.fvm1d.config;

import java.util.HashMap;

public class Config1D {

	private HashMap<Integer, Material> materials;
	private HashMap<Integer, Load> loads;
	private HashMap<Integer, Part> parts;
	private Control control;
	private HashMap<Integer, Output> outputs;
	
	public void init(){
		
	}
	
	public void putMaterial(Material material) {
		materials.put(material.getId(), material);
	}
	public void putLoad(Load load) {
		loads.put(load.getId(), load);
	}
	public void putPart(Part part) {
		parts.put(part.getId(), part);
	}
	public void putOutput(Output output) {
		outputs.put(output.getId(), output);
	}
	
	public HashMap<Integer, Material> getMaterials() {
		return materials;
	}
	public void setMaterials(HashMap<Integer, Material> materials) {
		this.materials = materials;
	}
	public HashMap<Integer, Load> getLoads() {
		return loads;
	}
	public void setLoads(HashMap<Integer, Load> loads) {
		this.loads = loads;
	}
	public HashMap<Integer, Part> getParts() {
		return parts;
	}
	public void setParts(HashMap<Integer, Part> parts) {
		this.parts = parts;
	}
	public Control getControl() {
		return control;
	}
	public void setControl(Control control) {
		this.control = control;
	}
	public HashMap<Integer, Output> getOutputs() {
		return outputs;
	}
	public void setOutputs(HashMap<Integer, Output> outputs) {
		this.outputs = outputs;
	}
	
	
	
}
