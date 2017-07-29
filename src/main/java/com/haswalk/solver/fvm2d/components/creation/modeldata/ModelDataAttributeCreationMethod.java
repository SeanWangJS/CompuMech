package com.haswalk.solver.fvm2d.components.creation.modeldata;

import java.util.List;

import com.haswalk.solver.fvm2d.config.Config;

public interface ModelDataAttributeCreationMethod {
	public List<?> invoke(int partId, Config config);
}
