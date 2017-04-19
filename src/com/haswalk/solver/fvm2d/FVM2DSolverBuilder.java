package com.haswalk.solver.fvm2d;

import java.util.HashMap;

import com.google.gson.reflect.TypeToken;
import com.haswalk.solver.Solver;
import com.haswalk.solver.SolverBuilder;
import com.haswalk.solver.fvm2d.config.Boundary;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.Control;
import com.haswalk.solver.fvm2d.config.Material;
import com.haswalk.solver.fvm2d.config.Output;
import com.haswalk.solver.fvm2d.config.Part;
import com.haswalk.solver.fvm2d.config.deserializer.BoundaryDeserializer;
import com.haswalk.solver.fvm2d.config.deserializer.MaterialDeserializer;
import com.haswalk.solver.fvm2d.config.initiation.PMLBoundaryInitiation;

public class FVM2DSolverBuilder implements SolverBuilder{

	private Config config = new Config();;
	
	@Override
	public SolverBuilder parseConfig(String configJson) {
		
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
		config.registInitiationMethod(new PMLBoundaryInitiation());
		
		config.parse(configJson);
		config.initConfigs();
		System.out.println(config.toString());
		return this;
	}

	@Override
	public Solver create() {

		HashMap<Integer, Blueprint> blueprintMap = new HashMap<>();
		config.getParts().forEach((partId, part) ->{
			blueprintMap.put(partId, 
						     Blueprint.strengthModelBlueprint.get(config.getMaterials()
						    		 									 .get(part.getMaterialID())
						    		 									 .getStrengthModelType()));
		});
		Assembler assembler = new Assembler(blueprintMap, config);
		assembler.assemble();
		FVM2DSolver solver = new FVM2DSolver();
		solver.setProcessors(assembler.getProcessors());
		assembler.getProcessors().forEach((name, p) -> {
			System.out.println(name);
		});
		return solver;
	}
}
