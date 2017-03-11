package com.haswalk.solver.fvm2d.config.deserializer;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.haswalk.solver.fvm2d.config.Material;
import com.haswalk.solver.fvm2d.config.material.StrengthModel;

public class MaterialDeserializer implements JsonDeserializer<Material>{

	@Override
	public Material deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Material material = gson.fromJson(json, Material.class);
		StrengthModel sm;
		try{
			sm = (StrengthModel) gson.fromJson(json.getAsJsonObject().get("strengthModel"), Material.strengthModelMap.get(material.getStrengthModelType()));
		}catch(Exception e) {
			System.err.println("Error: can not handle the strength model named: "+ material.getStrengthModelType() 
			+ "\n      ...And the analysis will be stoped."
			+ "\n      ...Please check out your config file.");
			throw new RuntimeException();
		}
		material.setStrengthModel(sm);
		return material;
	}
	
}
