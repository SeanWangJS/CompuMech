package com.haswalk.solver.fvm2d.components.creation.modeldata.boundary;

import com.haswalk.solver.fvm2d.components.modedata.BoundaryCondition;
import com.haswalk.solver.fvm2d.config.Config;

public interface BoundaryConditionCreationMethod {
	public BoundaryCondition invoke(int bcId, int partId, Config config);
}
