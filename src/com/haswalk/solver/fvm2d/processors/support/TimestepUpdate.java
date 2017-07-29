package com.haswalk.solver.fvm2d.processors.support;

import java.util.ArrayList;
import java.util.List;

import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.sean.utils.LsUtil;

public class TimestepUpdate implements Processor{

	private List<SinglePartTimestepUpdate> timeUpdaters;
	private TimeControl time;
	
	@Override
	public void calc() {
		List<Double> timesteps = new ArrayList<>();
		timeUpdaters.forEach(updater -> timesteps.add(updater.calc()));
		time.updateTimeStep(LsUtil.min(timesteps));
	}

	public void setTimeUpdaters(List<SinglePartTimestepUpdate> timeUpdaters){
		this.timeUpdaters = timeUpdaters;
	}
	
	public void setTime(TimeControl time){
		this.time = time;
	}
	
	public double getCurrentTime(){
		return time.getCurrentTime();
	}
	
	public boolean isTimesUp(){
		return time.isTimesUp();
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("TimeStepUpdate------------------------\n");
		timeUpdaters.forEach(u -> builder.append("time updater: \n"+u.toString() + "\n"));
		builder.append(time.toString());
		builder.append("TimeStepUpdate End--------------------\n");
		return builder.toString();
	}
}
