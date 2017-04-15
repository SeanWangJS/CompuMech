package com.haswalk.solver.fvm2d.components.creation.modeldata.boundary;

import com.haswalk.solver.fvm2d.components.modedata.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.modedata.StressBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.boundary.StressLoadBoundary;

public class StressBoundaryConditionCreation implements BoundaryConditionCreationMethod{

	@Override
	public BoundaryCondition invoke(int bcId, int partId, Config config) {
		StressLoadBoundary b = (StressLoadBoundary) config.getBoundaries().get(bcId);
		String type = config.getBoundaries().get(bcId).getType();
		return new StressBoundaryCondition(type, 
				  b.getLoad(), 
				  config.getParts().get(partId).getBoundaryCondition().getApplyNodesId(bcId));
	}

}
