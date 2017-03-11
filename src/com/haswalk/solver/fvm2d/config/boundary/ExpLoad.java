package com.haswalk.solver.fvm2d.config.boundary;

import java.util.Arrays;
import com.udojava.evalex.Expression;

public class ExpLoad extends Load{
	
	private double[][] t;
	private String[] value;
	
	@Override
	public void init() {
		function = time -> {
			boolean findInInterval = false;
			for(int i = 0; i < t.length; i++) {
				double[] interval = t[i];
				if(interval[0] <= time && time <= interval[1]) {
					findInInterval = true;
					return new Expression(value[i]).with("t", String.valueOf((time * 180 / Math.PI))).eval().doubleValue();
				}
			}
			if(!findInInterval) {
				return 0.0;
			}
			return 0.0;
		};
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < t.length; i++) {
			builder.append(Arrays.toString(t[i]) +":" + value[i] + "\n");
		}
		return builder.toString();
	}
	
}
