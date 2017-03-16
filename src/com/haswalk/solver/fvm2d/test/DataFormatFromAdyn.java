package com.haswalk.solver.fvm2d.test;

import org.junit.Test;

import com.sean.wang.utils.ArrayProcessor;
import com.sean.wang.utils.FileIO;

public class DataFormatFromAdyn {

	@Test
	public void test1(){
		String workspace = "C:/Users/wangx/OneDrive/workspace/ansys/adyn03132017-longitude";
		@SuppressWarnings("unchecked")
		ArrayProcessor<Double> ap = new ArrayProcessor<>().load(workspace + "/velx.uhs");
		@SuppressWarnings("unchecked")
		Double[][] sx = (Double[][])ap.take(3, ap.getLinesNumber() - 2)
		  .lineConvert(line -> {
			  String[] strs =((String) line).trim().split(",\\s*");
			  return new String[]{strs[1], strs[3]};
		  })
		  .typeParse(Double.class, a -> Double.parseDouble((String)a))
		  .get();
		FileIO.write(sx, "\t", "e:/fvm/7/velx_adyn.txt");
				
	}
	
}
