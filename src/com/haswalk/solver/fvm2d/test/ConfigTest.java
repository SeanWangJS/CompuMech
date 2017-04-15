package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.Control;
import com.haswalk.solver.fvm2d.config.Material;
import com.haswalk.solver.fvm2d.config.Output;
import com.haswalk.solver.fvm2d.config.Part;
import com.haswalk.solver.fvm2d.config.deserializer.BoundaryDeserializer;
import com.haswalk.solver.fvm2d.config.deserializer.MaterialDeserializer;

public class ConfigTest {
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void test() throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir"), "src/com/haswalk/solver/fvm2d/test/resource/config.json")));
		
		Config config = new Config();
		config.registConfigItem("boundaries", new HashMap<>())
			  .registConfigItem("materials", new HashMap<>())
			  .registConfigItem("parts", new HashMap<>())
			  .registConfigItem("control", new Control())
			  .registConfigItem("outputs", new HashMap<>());
		config.registItemType("boundaries", new TypeToken<HashMap<Integer, Boundary>>(){}.getType())
			  .registItemType("materials", new TypeToken<HashMap<Integer, Material>>(){}.getType())
			  .registItemType("parts", new TypeToken<HashMap<Integer, Part>>(){}.getType())
			  .registItemType("control", new TypeToken<Control>(){}.getType())
			  .registItemType("outputs", new TypeToken<HashMap<Integer, Output>>(){}.getType());
		config.registDeserializer(Material.class, new MaterialDeserializer())
			  .registDeserializer(Boundary.class, new BoundaryDeserializer());
		config.parse(json);
		config.initConfigs();
		System.out.println(config.toString());
			  
	}
}
