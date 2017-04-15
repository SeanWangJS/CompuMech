package com.haswalk.solver.fvm2d.config;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.haswalk.solver.fvm2d.config.initiation.InitiationMethod;
import com.haswalk.solver.fvm2d.util.Initiation;

public class Config {
	
	private GsonBuilder builder = new GsonBuilder();
	private Gson gson;
	
	private HashMap<String, Object> configItem = new HashMap<>();
	private HashMap<String, Type> itemType = new HashMap<>();
	private List<InitiationMethod> initMethods = new ArrayList<>(); 
	
	public void parse(String configStr) {
		gson = builder.setPrettyPrinting().create();
		JsonObject json = gson.fromJson(configStr, JsonObject.class);
		JsonObject configJson = json.get("config").getAsJsonObject();
		
		itemType.forEach((name, type) -> {
			configItem.put(name, gson.fromJson(configJson.get(name), type));
		});
	}

	public void initConfigs() {
		configItem.forEach((name, item) -> {
			if(item instanceof HashMap) {
				((HashMap<?,?>) item).forEach((id, val) -> {
					if(val instanceof Initiation) {
						((Initiation)val).init();
					}
				});
			}
		});
		initMethods.forEach(method -> method.invoke(this));
	}
	
	public Config registConfigItem(String name, Object item) {
		configItem.put(name, item);
		return this;
	}
	
	public Config registItemType(String name, Type type) {
		itemType.put(name, type);
		return this;
	}
	
	public Config registDeserializer(Class<?> clazz, JsonDeserializer<?> deserializer) {
		builder.registerTypeAdapter(clazz, deserializer);
		return this;
	}
	
	public Config registInitiationMethod(InitiationMethod method) {
		initMethods.add(method);
		return this;
	}
	
	public Object get(String name) {
		return configItem.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public Object get(String name, int id) {
		HashMap<Integer, ?> item;
		try{
			 item = (HashMap<Integer, ?>) configItem.get(name);
		}catch(Exception e) {
			throw new RuntimeException("Error: " + name + " is not a hashmap");
		}
		return item.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Material> getMaterials() {
		return (HashMap<Integer, Material>)configItem.get("materials");
	}
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Boundary> getBoundaries() {
		return (HashMap<Integer, Boundary>)configItem.get("boundaries");
	}
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Part> getParts() {
		return (HashMap<Integer, Part>)configItem.get("parts");
	}
	@SuppressWarnings("unchecked")
	public Boundary getBoundary(int id) {
		return (Boundary)((HashMap<Integer, Boundary>)configItem.get("boundaries")).get(id);
	}
	@SuppressWarnings("unchecked")
	public Part getPart(int id) {
		return (Part)((HashMap<Integer, Part>)configItem.get("parts")).get(id);
	}
	public Control getControl() {
		return (Control) configItem.get("control");
	}
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Output> getOutputs() {
		return (HashMap<Integer, Output>)configItem.get("outputs");
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		configItem.forEach((name, item) -> {
			builder.append(name + "-------------------------------\n")
				   .append(item.toString() + "\n")
				   .append("End " + name + "--------------------------\n");
		});
		return builder.toString();
	}
}
