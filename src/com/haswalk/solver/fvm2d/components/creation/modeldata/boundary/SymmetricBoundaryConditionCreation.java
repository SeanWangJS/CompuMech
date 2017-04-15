package com.haswalk.solver.fvm2d.components.creation.modeldata.boundary;

import com.haswalk.solver.fvm2d.components.modeldata.BoundaryCondition;
import com.haswalk.solver.fvm2d.components.modeldata.SymmetricBoundaryCondition;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.boundary.SymmetricBoundary;

public class SymmetricBoundaryConditionCreation implements BoundaryConditionCreationMethod{

	@Override
	public BoundaryCondition invoke(int bcId, int partId, Config config) {
		SymmetricBoundary b = (SymmetricBoundary) config.getBoundaries().get(bcId);;
		String type = config.getBoundaries().get(bcId).getType();
		return new SymmetricBoundaryCondition(type,
				config.getParts().get(partId).getBoundaryCondition().getApplyNodesId(bcId),
				b.getSymmetric());
	}

}
