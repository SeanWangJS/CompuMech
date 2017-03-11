package com.haswalk.solver.fvm2d.processors.extend;

import java.util.List;

import com.haswalk.solver.fvm2d.annotation.Inject;
import com.haswalk.solver.fvm2d.components.ModelData;
import com.haswalk.solver.fvm2d.components.TimeControl;
import com.haswalk.solver.fvm2d.processors.Processor;

public class Gauger implements Processor{

	private int inc = 1;
	private TimeControl time;
	private List<Integer> gaugeIDs;
	
	@Override
	public void calc() {
		if(!time.isTimesUp() && time.getCount() % inc != 0) {
			return;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Inject(ModelData.GAUGE_NODES_ID)
	public void setGaugeIDs(List<?> gaugeIDs){
		this.gaugeIDs = (List<Integer>) gaugeIDs;
	}
}
