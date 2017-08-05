package com.haswalk.solver.util.testresource;

import com.haswalk.solver.util.Serialize;

import java.util.List;

public class F {

	@Serialize
	private List<Integer> list;

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}
	
	public String toString() {
		return list.toString();
	}
}
