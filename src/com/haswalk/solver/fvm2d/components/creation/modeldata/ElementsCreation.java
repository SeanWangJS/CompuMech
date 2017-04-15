package com.haswalk.solver.fvm2d.components.creation.modeldata;

import java.util.List;

import com.haswalk.solver.fvm2d.config.Config;

public class ElementsCreation implements ModelDataAttributeCreationMethod{

	@Override
	public List<?> invoke(int partId, Config config) {
		return config.getParts().get(partId).getMesh().getElements();
	}

}
