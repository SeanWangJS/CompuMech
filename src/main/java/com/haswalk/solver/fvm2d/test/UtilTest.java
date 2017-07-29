package com.haswalk.solver.fvm2d.test;

import java.util.List;

import org.junit.Test;

import com.haswalk.solver.fvm2d.processors.Processor;
import com.haswalk.solver.fvm2d.processors.extend.Saver;
import com.haswalk.solver.fvm2d.util.FinalHit;
import com.haswalk.solver.fvm2d.util.ListableMap;

public class UtilTest {
	
	@Test
	public void test1() {
		ListableMap<String, Processor> processors = new ListableMap<>();
		Processor p1 = new Processor() {
			@Override
			public void calc() {}
		};
		
		Processor p2 = new Saver();
		
		processors.put("p1", p1)
		.put("p2", p2);
		
		List<FinalHit> hits = processors.getWhichIsImplementationOf(FinalHit.class);
		System.out.println(hits.size());
		
	}
	
}
