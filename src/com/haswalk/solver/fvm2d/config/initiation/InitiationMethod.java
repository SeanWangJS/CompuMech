package com.haswalk.solver.fvm2d.config.initiation;

import com.haswalk.solver.fvm2d.config.Config;

public interface InitiationMethod {
	public void invoke(Config config);
}
