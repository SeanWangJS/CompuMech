package com.haswalk.solver.fvm2d.config.output;

import java.util.List;

public class GaugeItem {
	private int inc;
	private List<String> items;
	
	public int getInc() {
		return inc;
	}

	public List<String> getItems() {
		return items;
	}

	public String toString() {
		return new StringBuilder().append("gauge item: \n")
			   .append("increment: " + inc + "\n")
			   .append("items: " + items + "\n")
			   .append("end\n").toString();
	}
}
