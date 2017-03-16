package com.haswalk.solver.fvm2d.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.haswalk.solver.fvm2d.util.ListableMap;
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
}
