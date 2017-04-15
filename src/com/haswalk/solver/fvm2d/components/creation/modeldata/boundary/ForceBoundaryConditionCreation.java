package com.haswalk.solver.fvm2d.components.creation.modeldata.boundary;

import com.haswalk.solver.fvm2d.components.modedata.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.modedata.ForceBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.boundary.CForceLoadBoundary;

public class ForceBoundaryConditionCreation implements BoundaryConditionCreationMethod{

	@Override
	public BoundaryCondition invoke(int bcId, int partId, Config config) {
		CForceLoadBoundary b = (CForceLoadBoundary) config.getBoundaries().get(bcId);
		String type = config.getBoundaries().get(bcId).getType();
		return new ForceBoundaryCondition(type, 
				  b.getLoad(),
				  config.getParts().get(partId).getBoundaryCondition().getApplyNodesId(bcId).get(0), 
				  b.getAngle());
	}
}
