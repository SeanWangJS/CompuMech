package com.haswalk.solver.fvm2d.test;

import org.junit.Test;

import com.haswalk.solver.fvm2d.util.ListableMap;

public class Mtest {

	@Test
	public void testListableMap() {
		ListableMap<String, String> map = new ListableMap<>();
		map.put("s", "S").put("a", "A");
		System.out.println(map.toString());
		
		
	}
	
}
