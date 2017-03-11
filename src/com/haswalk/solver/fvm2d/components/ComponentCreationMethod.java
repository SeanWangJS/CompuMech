package com.haswalk.solver.fvm2d.components;

import com.haswalk.solver.fvm2d.config.Config;

public interface ComponentCreationMethod {

	public Object invoke(int partId, Config config);
	
}
