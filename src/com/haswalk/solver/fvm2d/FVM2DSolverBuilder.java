package com.haswalk.solver.fvm2d;

import java.util.HashMap;
import com.haswalk.solver.Solver;
import com.haswalk.solver.SolverBuilder;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.initiation.PMLBoundaryHandle;

public class FVM2DSolverBuilder implements SolverBuilder{

	private Config config;
	
	@Override
	public SolverBuilder parseConfig(String configJson) {
		config = new Config();
		config.registInitiationMethod("PML", new PMLBoundaryHandle());
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
