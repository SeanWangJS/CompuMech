package com.haswalk.solver.fvm2d.config.output;

import java.util.List;

public class SaveItem {
	
	private int start;
	private int end;
	private int inc;
	private List<String> items;
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getInc() {
		return inc;
	}

	public List<String> getItems() {
		return items;
	}

	public String toString() {
		return new StringBuilder().append("save item: \n")
			   .append("increment: " + inc + "\n")
			   .append("items: " + items + "\n")
			   .append("end").toString();
	}
	
}
