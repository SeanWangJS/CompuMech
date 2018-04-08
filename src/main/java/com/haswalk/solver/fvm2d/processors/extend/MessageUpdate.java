package com.haswalk.solver.fvm2d.processors.extend;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;

import java.text.DecimalFormat;

public class MessageUpdate implements Processor{

	private TimeControl time;
	private long s = System.currentTimeMillis();
	private DecimalFormat format = new DecimalFormat("0.0000E0");

	@Override
	public void calc() {
		long now = System.currentTimeMillis();
		int count = time.getCount();
		double currentTime = time.getCurrentTime();
		double timeStep = time.getTimeStep();
		System.out.println(time.getCount() + ": " + time.getCurrentTime() + ": " + time.getTimeStep());
//		System.out.print("Circle: " + count +"\t|Time step: " + format.format(timeStep)
//				+ "\t|Current time: " + format.format(currentTime)+ "\r");
//		System.out.println(now - s);
		s = now;
	}

	@Injection
	public void setTime(TimeControl time){
		this.time = time;
	}
	
}
