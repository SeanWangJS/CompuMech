package com.haswalk.solver.fvm2d.components.creation.modeldata;

import java.util.List;

import com.haswalk.solver.fvm2d.config.Config;

public class NOECreation implements ModelDataAttributeCreationMethod{

	@Override
	public List<?> invoke(int partId, Config config) {
		return config.getParts().get(partId).getMesh().getNodesE();
	}

}
