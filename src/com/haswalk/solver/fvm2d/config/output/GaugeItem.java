package com.haswalk.solver.fvm2d.config.output;

import java.util.Arrays;

public class GaugeItem {
	private int inc;
	private String[] items;
	
	public String toString() {
		return new StringBuilder().append("gauge item: \n")
			   .append("increment: " + inc + "\n")
			   .append("items: " + Arrays.toString(items) + "\n")
			   .append("end").toString();
	}
}
