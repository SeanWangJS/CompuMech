package com.haswalk.solver.fvm2d;

import java.util.HashMap;
import com.haswalk.solver.fvm2d.components.ComponentFactory;
import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorFactory;
import com.haswalk.solver.fvm2d.processors.creation.TimestepUpdateCreationMethod;
import com.haswalk.solver.fvm2d.util.ListableMap;

public class Assembler {

	private ListableMap<String, Processor> processors;
	private Config config;
	private HashMap<Integer, Blueprint> blueprints;
	private ProcessorFactory processorFactory;
	
	public Assembler(HashMap<Integer, Blueprint> blueprints, Config config) {
		this.blueprints = blueprints;
		this.config = config;
	}
	
	public void assemble(){
		
		processors = new ListableMap<>();
		processorFactory = new ProcessorFactory();
		
		HashMap<Integer, Components> componentsMap = new HashMap<>();
		blueprints.forEach((partId, blueprint) -> {
			ComponentFactory componentFactory = new ComponentFactory(blueprint.getComponentBlueprint());
			componentsMap.put(partId, componentFactory.createAll(partId, config));
		});
		
		processors.put(Processor.TIMESTEP_UPDATE, 
					   processorFactory.create(new TimestepUpdateCreationMethod(), 
							   				   config, componentsMap)); 
		
		blueprints.forEach((partId, blueprint) -> {
			Components components = componentsMap.get(partId);
			blueprint.getProcessorMap().forEach((name, clazz) -> {
				processors.put(name + "_" + partId, processorFactory.create(name, clazz, components));
			});
		});
		
	}
	public ListableMap<String, Processor> getProcessors() {
		return processors;
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}
}
