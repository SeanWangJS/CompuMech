package com.haswalk.solver.fvm2d.processors.extend;

import com.haswalk.solver.fvm2d.annotation.Injection;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;

public class MessageUpdate implements Processor{

	private TimeControl time;
	
	private long s = System.currentTimeMillis();
	
	@Override
	public void calc() {
		long now = System.currentTimeMillis();
		System.out.println(time.getCount() + ": " + time.getCurrentTime() + ": " + time.getTimeStep());
		System.out.println(now - s);
		s = now;
	}
	
	@Injection
	public void setTime(TimeControl time){
		this.time = time;
	}
	
}
