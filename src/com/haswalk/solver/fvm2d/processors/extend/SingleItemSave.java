package com.haswalk.solver.fvm2d.processors.extend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SingleItemSave {
	private int partId;
	private String item;
	private double[] value;
	private String workspace;
	
	public SingleItemSave(int partId, String item, double[] value, String workspace) {
		this.partId = partId;
		this.item = item;
		this.value = value;
		this.workspace = workspace;
	}

	public void write(int count){
		StringBuilder builder = new StringBuilder();
		for(double v: value) {
			builder.append(v + "\r\n");
		}
		FileWriter writer;
		try {
			new File(workspace +"/"+partId).mkdir();
			writer = new FileWriter(new File(workspace +"/"+ partId + "/" +item + "_" + count + ".txt"));
			writer.write(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
