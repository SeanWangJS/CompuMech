package com.haswalk.solver.fvm2d.components.creation;

import com.haswalk.solver.fvm2d.components.ComponentCreationMethod;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.config.Config;
import com.haswalk.solver.fvm2d.config.control.TimeConfig;

public class TimeControlCreationMethod implements ComponentCreationMethod{

	@Override
	public Object invoke(int partId, Config config) {
		TimeConfig tc = config.getControl().getTime();
		TimeControl timeControl = TimeControl.create();
		timeControl.setTimeConfig(tc.getEndTime(), tc.getFactor());
		return timeControl;
	}

}
