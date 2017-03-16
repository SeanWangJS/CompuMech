package com.haswalk.solver.fvm2d.processors;

import java.util.HashMap;

import com.haswalk.solver.fvm2d.components.Components;
import com.haswalk.solver.fvm2d.config.Config;

public interface ProcessorCreationMethod {
	
	public Processor invoke(int partId, Config config, HashMap<Integer, Components> componentsMap);
	
}
