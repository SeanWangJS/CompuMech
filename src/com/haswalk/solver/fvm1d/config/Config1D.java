package com.haswalk.solver.fvm1d.config;

import java.util.HashMap;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.util.ToString;

public class Config1D implements ToString{

	@Serialize
	private HashMap<Integer, Material> materials = new HashMap<>();
	@Serialize
	private HashMap<Integer, BoundaryCondition> boundaries = new HashMap<>();
	@Serialize
	private HashMap<Integer, Part> parts = new HashMap<>();
	@Serialize
	private Control control;
	@Serialize
	private HashMap<Integer, Output> outputs = new HashMap<>();
	
	public void init(){
		
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
	public void putMaterial(Material material) {
		materials.put(material.getId(), material);
	}
	public void putLoad(BoundaryCondition load) {
		boundaries.put(load.getId(), load);
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
	public HashMap<Integer, BoundaryCondition> getBoundaies() {
		return boundaries;
	}
	public void setBoundaries(HashMap<Integer, BoundaryCondition> boundaries) {
		this.boundaries = boundaries;
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
