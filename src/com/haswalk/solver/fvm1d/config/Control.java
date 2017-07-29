package com.haswalk.solver.fvm1d.config;

import com.haswalk.jsonutil.Serialize;
import com.haswalk.solver.fvm1d.config.support.control.TimeControl;
import com.haswalk.solver.fvm1d.util.ToString;

public class Control implements ToString{

	private Config1DBuilder builder;
	
	@Serialize
	private TimeControl time; 
	
	public Control(Config1DBuilder builder) {
		this.builder = builder;
	}
	
	public TimeControl time() {
		time = new TimeControl(this);
		return time;
	}
	
	public Config1DBuilder build() {
		builder.setControl(this);
		return builder;
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
}
