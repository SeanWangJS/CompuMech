package com.haswalk.solver.fvm2d.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.haswalk.solver.fvm2d.util.Geom;
import com.haswalk.solver.fvm2d.util.MeshProcessor;

public class MeshProcessorTest {

	@Test
	public void test1() {
		List<double[]> vertices = new ArrayList<>();
		vertices.add(new double[]{0, 0});
		vertices.add(new double[]{1, 0});
		vertices.add(new double[]{0, 1});
		vertices.add(new double[]{1, 1});
		vertices.add(new double[]{0, 2});
		vertices.add(new double[]{1, 2});
		vertices.add(new double[]{2, 0});
		vertices.add(new double[]{2, 1});
		vertices.add(new double[]{2, 2});
		
		List<int[]> elements = new ArrayList<>();
		elements.add(new int[]{0, 1, 3, 2});
		elements.add(new int[]{2, 3, 5, 4});
		elements.add(new int[]{1, 6, 7, 3});
		elements.add(new int[]{3, 7, 8, 5});
		
		MeshProcessor mp = new MeshProcessor(vertices, elements);
		mp.handle();
		System.out.println(mp.getSurrE());
	}
	
	@Test
	public void test2() {
		double px = -1;
		double py = 1;
		System.out.println(Geom.pole_theta(px, py));
	}

}
