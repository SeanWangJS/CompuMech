package com.haswalk.solver.fvm2d.processors.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.util.FinalHit;

public class Gauger implements Processor, FinalHit{

	private int inc = 1;
	private String workspace;
	private TimeControl time;
	private List<SingleItemGauge> gauges = new ArrayList<>();
	private List<Double> t = new ArrayList<>();;
	
	@Override
	public void calc() {
		if(!time.isTimesUp() && time.getCount() % inc != 0) {
			return;
		}
		t.add(time.getCurrentTime());
		gauges.forEach(g -> g.record());
	}
	
	public void set(String workspace, int inc, int partId, TimeControl time, List<Integer> gaugeNodesID, 
			HashMap<String, double[]> data){
		this.workspace = workspace;
		this.inc = inc;
		this.time = time;
		data.forEach((item, d) -> gauges.add(new SingleItemGauge(partId, item, gaugeNodesID, d)));
	}

	@Override
	public void hit() {
		gauges.forEach(g -> g.write(workspace, t));
	}

}
