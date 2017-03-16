package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import com.haswalk.solver.fvm2d.Blueprint;
import com.haswalk.solver.fvm2d.blueprint.ElasticModelBlueprint;
import com.haswalk.solver.fvm2d.components.ComponentFactory;
import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.ProcessorFactory;
import com.haswalk.solver.fvm2d.processors.creation.TimestepUpdateCreationMethod;
import com.haswalk.solver.fvm2d.processors.support.DefaultDispUpdate;
import com.haswalk.solver.fvm2d.processors.support.TimestepUpdate;
import com.haswalk.solver.fvm2d.util.ListableMap;

public class ProcessorFactoryTest {
	private Config config;
	
	@Before
	public void before() throws IOException{
		String json = new String(Files.readAllBytes(Paths.get("E:/fvm/7/config.json")));
		config = new Config();
		config.parse(json);
		config.initConfigs();
	}
	@Test
	public void test1(){
		Blueprint blueprint = new ElasticModelBlueprint();
		
		HashMap<Integer, Components> componentsMap = new HashMap<>();
		ComponentFactory factory = new ComponentFactory(blueprint.getComponentCreationMap());
		config.getParts().forEach((partId, part) -> {
			componentsMap.put(partId, factory.createAll(partId, config));
		});
		
		TimestepUpdate tsu = (TimestepUpdate) new TimestepUpdateCreationMethod().invoke(0, config, componentsMap);
		System.out.println(tsu.toString());
		tsu.calc();
		System.out.println("current time: " + tsu.getCurrentTime());
	}
	
	@Test
	public void testDispUpdate(){
		Blueprint blueprint = new ElasticModelBlueprint();
		ComponentFactory factory = new ComponentFactory(blueprint.getComponentCreationMap());
		Components components = factory.createAll(1, config);
		ProcessorFactory processorFactory = new ProcessorFactory();
		processorFactory.create(Processor.DISP_UPDATE, DefaultDispUpdate.class, components);
	}
	
	@Test
	public void test2(){
		Blueprint blueprint = new ElasticModelBlueprint();
		ComponentFactory factory = new ComponentFactory(blueprint.getComponentCreationMap());
		Components components = factory.createAll(1, config);
		ProcessorFactory processorFactory = new ProcessorFactory();
		ListableMap<String, Processor> processors = new ListableMap<>();
		blueprint.getProcessorMap().forEach((name, clazz) -> {
			processors.put(name + "_" + 1, processorFactory.create(name, clazz, components));
		});
		processors.forEach((name, processor) -> System.out.println(name));
	}
	
}
