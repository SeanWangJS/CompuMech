package com.haswalk.solver.fvm2d.config.boundary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoad extends Load{
	private String uri;

	private double[] t;
	private double[] value;
	
	@Override
	public void init() {
		List<double[]> load = null;
		try {
			load = Stream.of(new String(Files.readAllBytes(Paths.get(uri))).split("\r\n"))
					.map(line -> {
						String[] ss = line.split("\\s+");
						return new double[]{Double.valueOf(ss[0]), Double.valueOf(ss[1])};
					})
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert load != null;
		t = new double[load.size()];
		value = new double[load.size()];
		
		for(int i = 0, len = load.size(); i < len; i++) {
			t[i] = load.get(i)[0];
			value[i] = load.get(i)[1];
		}
		
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
		return "uri: " + uri + "\n";
		
	}
}
