package com.haswalk.solver.util.testresource;

import com.haswalk.solver.util.Serialize;

import java.util.Arrays;
import java.util.List;


public class E {
	
	private double p1;
	private D d;
	
	private char[] cs;
	
	private boolean[] bs;
	
	private long[] ls;
	
	@Serialize
	private double[] arr;
	
	@Serialize
	private Integer[] iArr;
	
	@Serialize
	private int[][] i2;
	
	private List<Integer> iList;
	private List<Double> dList;
	
	@Serialize
	private D3[] d3;
	
	public String toString() {
		return Arrays.toString(arr) + "\r\n" + Arrays.toString(iArr) + "\r\n" + Arrays.toString(i2[0]) 
		+ "\r\n" + d3[0].toString();
	}
	
	public void setArr(double[] arr) {
		this.arr = arr;
	} 
	
	public void setIList(List<Integer> iList){
		this.iList = iList;
	}
	
	public void setDList(List<Double> dList) {
		this.dList = dList;
	}
	public void setIArr(Integer[] iArr) {
		this.iArr = iArr;
	}
	public void setI2(int[][] i2) {
		this.i2 = i2;
	}
	public void setD3(D3[] d3) {
		this.d3 = d3;
	}
}
