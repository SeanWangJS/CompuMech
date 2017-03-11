package com.haswalk.solver.fvm2d.config.control;

public class TimeConfig {
	private double endTime;
	private double factor;
	
	public String toString() {
		return new StringBuilder().append("time control: \n")
				.append("end time: " + endTime + "\n")
				.append("end").toString();
	}
	
	public double getEndTime(){
		return endTime;
	}
	public double getFactor(){
		return factor;
	}
}
