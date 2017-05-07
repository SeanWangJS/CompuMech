package com.haswalk.solver.fvm2d.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.haswalk.solver.fvm2d.util.ListableMap;
import com.sean.wang.utils.ArrUtil;
import com.sean.wang.utils.FileIO;
import com.udojava.evalex.Expression;

public class Mtest {

	@Test
	public void testListableMap() {
		ListableMap<String, String> map = new ListableMap<>();
		map.put("s", "S").put("a", "A");
		System.out.println(map.toString());
		
		
	}
	
	@Test
	public void testMakeDir() throws IOException{
		File file = new File("E:/fvm/7/1");
		file.mkdir();
		System.out.println(file.exists());
	}
	
	@Test
	public void testSingleton(){
		ListableMap<String, A> map = new ListableMap<>();
		map.put("a1", A.create());
		map.put("a2", A.create());
		map.put("a3", A.create());
		map.get("a1").inc();
		map.get("a1").inc();
		System.out.println(map.get("a3").b);
		
		A a = A.create();
		A a2 = a;
		a2.inc();
		System.out.println(a.b);
		
		System.out.println(map.get("a1").hashCode());
		System.out.println(map.get("a2").hashCode());
		
		System.out.println(a.hashCode());
		System.out.println(a2.hashCode());
	}
	
	@Test
	public void test2(){
		A a = A.create();
		System.out.println(a.hashCode());
	}
	
	static class A{
		int b = 0;
		static A a = null; 
		private A(){
			
		}
		public static A create(){
			if(a == null) {
				a = new A();
			}
			return a;
		}
		
		public void inc(){
			b++;
		}
		
	}
	
	@Test
	public void test3(){
		
		double value = 6.430552877066282E-4;
		System.out.println(String.valueOf(value));
		double k = new Expression("t + 1").with("t", new BigDecimal(value)).eval().doubleValue();
		System.out.println(k);
	}
	
	@Test
	public void test4() {
		HashMap<String, B> map = new HashMap<>();
		map.put("b1", new B("b1"));
		System.out.println(map.toString());
	}
	class B {
		private String b;
		public B(String b) {
			this.b = b;
		}
		public String toString() {
			return b + "csdk\n";
		}
	}
	
	@Test
	public void test5() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0);
		list.remove(0);
		System.out.println(list);
	}
	
	@Test
	public void test6() {
		List<double[]> pre = FileIO.readDoubleArrList("E:/fvm/24/pressure.csv", ",");
		double[][] pressure1 = new double[pre.size()][];
		double[][] pressure2 = new double[pre.size()][];
		for(int i = 0, len = pre.size(); i < len; i++) {
			double[] p =pre.get(i);
			pressure1[i] = new double[]{p[0] / 1000.0, p[1] * 10e6};
			pressure2[i] = new double[]{p[0], p[1] * 10e3};
		}
		
		FileIO.writeDouble2DArr(pressure1, "E:/fvm/24/pressure.txt", "\t");
		FileIO.write(ArrUtil.toStandardFormatString(pressure2, 5), "E:/fvm/24/pressure.uhs");
		
	}
	
	@Test
	public void test7() {
		List<double[]> vertices = FileIO.readDoubleArrList("E:/fvm/24/vertices.txt", "\\s+");
		List<double[]> verts = vertices.stream().map(v -> {
			return new double[]{v[0] * 10, v[1] * 10};
		}).collect(Collectors.toList());
		FileIO.writeDoubleArrList(verts, "E:/fvm/25/vertices.txt", "\t");
	}
}
