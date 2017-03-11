package com.haswalk.solver.fvm2d.config;

import com.haswalk.solver.fvm2d.config.control.TimeConfig;

public class Control {

	private TimeConfig time;
	
	public String toString() {
		return time.toString();
	}
	
	public TimeConfig getTime(){
		return time;
	}
}
