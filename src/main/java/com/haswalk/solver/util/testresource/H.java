package com.haswalk.solver.util.testresource;

import java.util.Map;
import com.haswalk.solver.util.Serialize;

public class H {

	@Serialize
	private Map<Integer, Double> map;
	
	public void setMap(Map<Integer, Double> map){
		this.map = map;
	}
	
	public String toString() {
		return map.toString();
	}
}
