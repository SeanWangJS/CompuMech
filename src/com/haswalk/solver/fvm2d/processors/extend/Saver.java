package com.haswalk.solver.fvm2d.processors.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.util.FinalHit;

public class Saver implements Processor, FinalHit{

	private int inc;
	private TimeControl time;
	private List<SingleItemSave> ss = new ArrayList<>();
	
	@Override
	public void calc() {
		if(inc > 0 && time.getCount() % inc == 0) {
			ss.forEach(s -> s.write(time.getCount()));	
			return;
		}
		return;
	}
	
	@Override
	public void hit() {
		ss.forEach(s -> s.write(time.getCount()));	
	}
	
	public void set(String workspace, int inc, int partId, TimeControl time, HashMap<String, double[]> data){
		this.inc = inc;
		this.time = time;
		data.forEach((item, d) -> ss.add(new SingleItemSave(partId, item, d, workspace)));
	}
}
