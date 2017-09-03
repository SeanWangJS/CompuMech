package com.haswalk.solver.fvm2d;

import java.util.List;

import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.support.TimestepUpdate;
import com.haswalk.solver.fvm2d.util.FinalHit;
import com.haswalk.solver.util.ListableMap;

public class FVM2DSolver implements Solver{

	private ListableMap<String, Processor> processors;
	
	private void before(){
		
	}
	private void after(){
		List<FinalHit> hits = processors.getWhichIsImplementationOf(FinalHit.class);
		hits.forEach(FinalHit::hit);
		
	}
	
	@Override
	public Solver run() {
		before();
		while(true) {
			
			processors.forEach((name, p) -> p.calc());
			if(((TimestepUpdate)processors.get(Processor.TIMESTEP_UPDATE)).isTimesUp()) {
				break;
			}
		}
		System.out.println("complete");
		after();
		return this;
	}
	
	public void setProcessors(ListableMap<String, Processor> processors) {
		this.processors = processors;
	}

	@Override
	public Object getResult() {
		return null;
	}

}
