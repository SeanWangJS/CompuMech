package com.haswalk.solver.fvm1d.config;

import java.util.HashMap;

import com.haswalk.jsonutil.Serialize;

public class Config1D {

	@Serialize
	private HashMap<Integer, Material> materials;
	@Serialize
	private HashMap<Integer, Boundary> boundaries;
	@Serialize
	private HashMap<Integer, Part> parts;
	@Serialize
	private Control control;
	@Serialize
	private HashMap<Integer, Output> outputs;
	
	public void init(){
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("materials: \r\n" + materials.toString() + "\r\nboundaries: \r\n"  
		+ boundaries.toString() + "\r\nparts: \r\n" + parts.toString() + "\r\ncontrol: \r\n" + control.toString() 
		+ "\r\noutputs: \r\n" + outputs.toString() + "\r\n"); 
		return super.toString();
	}
	
	public void putMaterial(Material material) {
		materials.put(material.getId(), material);
	}
	public void putLoad(Boundary load) {
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
	public HashMap<Integer, Boundary> getBoundaies() {
		return boundaries;
	}
	public void setBoundaries(HashMap<Integer, Boundary> boundaries) {
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
