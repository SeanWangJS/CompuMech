package com.haswalk.solver.fvm2d.components;

import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.util.ListableMap;

public class ComponentFactory {

	private ListableMap<String, ComponentCreationMethod> componentBlueprint;
	
	public ComponentFactory(ListableMap<String, ComponentCreationMethod> componentBlueprint){
		this.componentBlueprint = componentBlueprint;
	}
	
	private Object create(ComponentCreationMethod method, int partId, Config config){
		return method.invoke(partId, config);
	}
	
	public Components createAll(int partId, Config config) {
		Components components = new Components();
		componentBlueprint.forEach((name, method) -> {
			components.put(name, create(method, partId, config));
		});
		return components;
	}
}
