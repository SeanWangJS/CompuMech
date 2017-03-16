package com.haswalk.solver.fvm2d.components;

import com.haswalk.solver.fvm2d.annotation.Component;

@Component
public class TimeControl {

	private double endTime;
	private double factor;
	
	private double currentTime;
	private double timeStep;
	private double timeStepLst;
	private int count = 0;
	
	private static TimeControl timeControl = null;
	
	private TimeControl(){
		
	}
	public static TimeControl create(){
		if(timeControl == null) {
			timeControl = new TimeControl();
		}
		return timeControl;
	}
	
	public String toString() {
		return new StringBuilder().append("TimeControl-------------------------------\n")
				.append("end time: " + endTime + "\n")
				.append("factor: " + factor + "\n")
				.append("TimeControl End----------------------------\n").toString();
	}
	
	public void setTimeConfig(double endTime, double factor){
		this.endTime = endTime;
		this.factor = factor;
	}
	
	public double getCurrentTime(){
		return currentTime;
	}
	
	public double getTimeStep(){
		return timeStep;
	}
	
	public double getTimeStepLst() {
		return timeStepLst;
	}
	
	public double getEndTime(){
		return endTime;
	}
	
	public boolean isTimesUp(){
		return endTime <= currentTime;
	}
	
	public int getCount() {
		return count;
	}
	public void updateTimeStep(double newTimeStep){
		timeStepLst = timeStep;
		timeStep = newTimeStep * factor;
		currentTime += timeStep;
		count++;
	}

}
