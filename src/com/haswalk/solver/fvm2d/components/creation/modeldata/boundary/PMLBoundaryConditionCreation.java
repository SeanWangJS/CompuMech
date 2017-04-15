package com.haswalk.solver.fvm2d.components.creation.modeldata.boundary;

import com.haswalk.solver.fvm2d.components.modeldata.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.modeldata.PMLBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.boundary.PMLBoundary;

public class PMLBoundaryConditionCreation implements BoundaryConditionCreationMethod{

	@Override
	public BoundaryCondition invoke(int bcId, int partId, Config config) {
		PMLBoundary b = (PMLBoundary) config.getBoundaries().get(bcId);
		return new PMLBoundaryCondition(config.getBoundary(bcId).getType(), 
												config.getParts().get(partId).getBoundaryCondition().getApplyNodesId(bcId),
												b.getDist(),
												b.getPMLNodesIds(),
												b.getDelta());
	}

}
