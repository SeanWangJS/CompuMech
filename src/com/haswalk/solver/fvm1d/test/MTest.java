package com.haswalk.solver.fvm1d.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sean.wang.utils.FileIO;

public class MTest {

	@Test
	public void meshGenerate() {
		double left = 0;
		double right = 100;
		double NON = 101;
		double width = (right - left) / (NON - 1);
		List<Double> vertices = new ArrayList<>();
		List<int[]> elements = new ArrayList<>();
		for(int i = 0; i < NON; i++) {
			vertices.add(left + width * i);
		}
		for(int i = 0; i < NON - 1; i++) {
			elements.add(new int[]{i+1, i});
		}
		FileIO.writeDoubleList(vertices, "E:/fvm1d/1/vertices.txt");
		FileIO.writeIntArrList(elements, "E:/fvm1d/1/elements.txt", "\t");
		FileIO.StandardFormat1DMesh.writeToOne(vertices, elements, "E:/fvm1d/1/mesh.txt");
	}
	
}
