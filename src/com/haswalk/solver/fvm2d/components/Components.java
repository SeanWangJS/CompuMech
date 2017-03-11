package com.haswalk.solver.fvm2d.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Components {
	
	public final static String TIME_CONTROL = "TimeControl";
	public final static String FIELD_DATA = "FieldData";
	public final static String MODEL_DATA = "ModelData";
	public final static String MATERIAL_PROPERTY = "MaterialProperty";
	
	private HashMap<String, Object> components = new HashMap<>();
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Components+++++++++++++++++++++++++++++\n");
		components.forEach((name, comp)  -> {
			builder.append("name:\n")
				   .append(comp.toString() +"\n");
			
		});
		builder.append("Components End+++++++++++++++++++++++++\n");
		return builder.toString();
	}
	
	public Object get(String name){
		return components.get(name);
	}
	
	public void put(String name, Object compoent){
		components.put(name, compoent);
	}

	public List<Object> getAll(){
		List<Object> all = new ArrayList<>();
		components.forEach((name, comp) -> all.add(comp));
		return all;
	}
	
}
