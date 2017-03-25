package com.haswalk.solver.fvm2d.config;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.haswalk.solver.fvm2d.config.deserializer.BoundaryDeserializer;
import com.haswalk.solver.fvm2d.config.deserializer.MaterialDeserializer;
import com.haswalk.solver.fvm2d.config.initiation.InitiationMethod;
import com.haswalk.solver.fvm2d.util.ListableMap;

public class Config {
	
	private Gson gson = new GsonBuilder()
			.registerTypeAdapter(Material.class, new MaterialDeserializer())
			.registerTypeAdapter(Boundary.class, new BoundaryDeserializer())
			.setPrettyPrinting().create();
	
	private HashMap<Integer, Material> materials;
	private HashMap<Integer, Boundary> boundaries;
	private HashMap<Integer, Part> parts;
	private Control control;
	private HashMap<Integer, Output> outputs;
	
	private ListableMap<String, InitiationMethod> initiationItems = new ListableMap<>();
	
	public void parse(String configStr){
		materials = new HashMap<>();
		boundaries = new HashMap<>();
		parts = new HashMap<>();
		
		JsonObject json = gson.fromJson(configStr, JsonObject.class);
		JsonObject configJson = json.get("config").getAsJsonObject();
		
		Type type1 = new TypeToken<HashMap<Integer, Material>>(){}.getType();
		Type type2 = new TypeToken<HashMap<Integer, Boundary>>(){}.getType();
		Type type3 = new TypeToken<HashMap<Integer, Part>>(){}.getType();
		Type type4 = new TypeToken<HashMap<Integer, Output>>(){}.getType();
		
		materials = gson.fromJson(configJson.get("materials"), type1);

		boundaries = gson.fromJson(configJson.get("boundaries"), type2);

		parts = gson.fromJson(configJson.get("parts"), type3);
		
		control = gson.fromJson(configJson.get("control"), Control.class);
		
		outputs = gson.fromJson(configJson.get("outputs"), type4);
		
	}

	public void initConfigs() {
		materials.forEach((id, material) -> material.init());
		boundaries.forEach((id, boundary) -> boundary.init());
		parts.forEach((id, part) -> part.init());
		initiationItems.forEach((name, method) -> method.invoke(this));
	}
	
	public HashMap<Integer, Material> getMaterials() {
		return materials;
	}

	public HashMap<Integer, Boundary> getBoundaries() {
		return boundaries;
	}
	
	public Boundary getBoundary(int id) {
		return boundaries.get(id);
	}

	public HashMap<Integer, Part> getParts() {
		return parts;
	}

	public Part getPart(int id) {
		return parts.get(id);
	}
	
	public Control getControl() {
		return control;
	}

	public HashMap<Integer, Output> getOutputs() {
		return outputs;
	}
	
	public Config registInitiationMethod(String name, InitiationMethod method) {
		initiationItems.put(name, method);
		return this;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Materials----------------------------------\n");
		getMaterials().forEach((id, material)-> builder.append(id + "\n" + material.toString() + "\n"));
		builder.append("Materials End------------------------------\n");
		builder.append("Boundaries---------------------------------\n");
		getBoundaries().forEach((id, boundary) -> builder.append(id + "\n" + boundary.toString()));
		builder.append("Boundaries End-----------------------------\n");
		builder.append("Parts--------------------------------------\n");
		getParts().forEach((id, part) -> builder.append(id + "\n" + part.toString()));
		builder.append("Parts End----------------------------------\n");
		builder.append("Control------------------------------------\n");
		builder.append(getControl() + "\n");
		builder.append("Control End--------------------------------\n");
		builder.append("Outputs------------------------------------\n");
		getOutputs().forEach((id, output) -> builder.append(id + "\n" + output.toString()));
		builder.append("Outputs End--------------------------------\n");
		return builder.toString();
	}
}
