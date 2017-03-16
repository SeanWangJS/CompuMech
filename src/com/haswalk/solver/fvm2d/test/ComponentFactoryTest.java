package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.haswalk.solver.fvm2d.Blueprint;
import com.haswalk.solver.fvm2d.blueprint.ElasticModelBlueprint;
import com.haswalk.solver.fvm2d.components.ComponentFactory;
import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.config.Config;

public class ComponentFactoryTest {
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
		
		ComponentFactory cf = new ComponentFactory(blueprint.getComponentCreationMap());
		Components components = cf.createAll(1, config);
		System.out.println(components.toString());
	}
	
	
}
