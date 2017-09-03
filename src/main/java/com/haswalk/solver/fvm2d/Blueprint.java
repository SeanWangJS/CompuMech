package com.haswalk.solver.fvm2d;

import java.util.HashMap;

import com.haswalk.solver.fvm2d.blueprint.ElasticModelBlueprint;
import com.haswalk.solver.fvm2d.components.ComponentCreationMethod;
import com.haswalk.solver.fvm2d.processors.ProcessorCreationMethod;
import com.haswalk.solver.util.ListableMap;

public abstract class Blueprint {

	public final static String ELASTIC_STRENGTH_MODEL = "elastic";
	public final static String MOHR_COULOMB_STRENGTH_MODEL = "mohrCoulomb";
	
	public static HashMap<String, Blueprint> strengthModelBlueprint = new HashMap<>();
	static{
		strengthModelBlueprint.put(ELASTIC_STRENGTH_MODEL, new ElasticModelBlueprint());
	}
	
	private ListableMap<String, Class<?>> processorMap = new ListableMap<>();
	private ListableMap<String, ComponentCreationMethod> componentCreationMap = new ListableMap<>();
	private ListableMap<String, ProcessorCreationMethod> processorCreationMap = new ListableMap<>();
	
	public Blueprint registProcessor(String name, Class<?> clazz){
		processorMap.put(name, clazz);
		return this;
	}
	public Blueprint registComponentCreationMethod(String name , ComponentCreationMethod method) {
		componentCreationMap.put(name, method);
		return this;
	}
	public Blueprint registProcessorCreationMethod(String name, ProcessorCreationMethod method){
		processorCreationMap.put(name, method);
		processorMap.put(name, null);
		return this;
	}
	public ListableMap<String, Class<?>> getProcessorMap(){
		return processorMap;
	}
	public ListableMap<String, ComponentCreationMethod> getComponentCreationMap(){
		return componentCreationMap;
	}
	public ListableMap<String, ProcessorCreationMethod> getProcessorCreationMap(){
		return processorCreationMap;
	}
	
}
