package com.haswalk.solver.fvm2d.config.boundary;

public class PiecewiseLoad extends Load{

	private double[] t;
	private double[] value;

	@Override
	public void init() {
		function = time -> {
			if(time >= t[t.length - 1] || time <= t[0]) {
				return 0.0;
			}
			int left = 0;
			for(int i = 0, len = t.length - 1; i < len; i++) {
				if(t[i] <= time && time < t[i + 1]){
					left = i;
				}
			}
			return value[left] + (value[left + 1] - value[left]) * (time - t[left]) / (t[left + 1] - t[left]);
		};
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < t.length; i++) {
			builder.append(t[i] +": "+ value[i]+"\n");
		}
		return builder.toString();
	}
	
}
