package com.haswalk.solver.fvm2d.config.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.boundary.Load;
import com.haswalk.solver.fvm2d.config.boundary.LoadBoundary;

public class BoundaryDeserializer implements JsonDeserializer<Boundary> {

	@Override
	public Boundary deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonObject boundaryJson = json.getAsJsonObject();
		String type = boundaryJson.get("type").getAsString();
//		Class<?> neededBoundaryClazz = null;
//		if (Boundary.FORCE.equals(type) || Boundary.STRESS.equals(type)) {
//			String loadType = boundaryJson.get("loadType").getAsString();
//			neededBoundaryClazz = LoadBoundary.loadMap.get(loadType);
//		} else {
//			neededBoundaryClazz = Boundary.typeMap.get(type);
//		}
		
		Class<?> neededBoundaryClazz = Boundary.typeMap.get(type);

		Boundary boundary = null;
		try {
			boundary = (Boundary) gson.fromJson(json, neededBoundaryClazz);
		} catch (Exception e) {
			System.err.println("Error: can not handle the boundary: " + type
					+ "\n      ...And the analysis will be stoped." 
					+ "\n      ...Please check out your config file.");
			throw new RuntimeException();
		}
		if (Boundary.FORCE.equals(type) || Boundary.STRESS.equals(type)) {
			String loadType = boundaryJson.get("loadType").getAsString();
			Load load = (Load) gson.fromJson(boundaryJson.get("load"), LoadBoundary.loadMap.get(loadType));
			((LoadBoundary)boundary).setLoad(load);
		}
		return boundary;
	}

}
