package com.haswalk.solver.util.testresource;

import java.util.Set;

import com.haswalk.solver.util.Serialize;

public class G {

	@Serialize
	private Set<Integer> set;
	
	public void setSet(Set<Integer> set) {
		this.set = set; 
	}
	
	public String toString() {
		return set.toString();
	}
}
