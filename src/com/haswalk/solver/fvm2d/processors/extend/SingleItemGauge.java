package com.haswalk.solver.fvm2d.processors.extend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sean.wang.utils.ArrUtil;

public class SingleItemGauge {

	private int partId;
	private String item;
	private List<Integer> gaugeNodesID;
	private double[] data;
	private List<double[]> result;

	public SingleItemGauge(int partId, String item, List<Integer> gaugeNodesID, double[] data){
		result = new ArrayList<>();
		this.partId = partId;
		this.item = item;
		this.gaugeNodesID = gaugeNodesID;
		this.data = data;
	}
	
	public void record(){
		result.add(ArrUtil.take(data, gaugeNodesID));
	}
	public void write(String workspace, List<Double> t){
		StringBuilder builder = new StringBuilder();
		for(int i = 0, len = t.size(); i < len; i++) {
			double time = t.get(i);
			double[] value = result.get(i);
			builder.append(time);
			for(double v: value){
				builder.append("\t" + v);
			}
			builder.append("\r\n");
		}
		try {
			new File(workspace +"/"+partId).mkdir();
			FileWriter writer = new FileWriter(new File(workspace +"/"+partId+ "/" + item + ".txt"));
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
