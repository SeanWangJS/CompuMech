package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

public class SolverBuilderTest {
	private String json = null;
	
	@Before
	public void before() throws IOException{
		json = new String(Files.readAllBytes(Paths.get("E:/fvm/21/config.json")));
	}
	@Test
	public void test1(){
		FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
		builder.parseConfig(json).create();
	} 
	
	@Test
	public void testSolve(){
		FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
		Solver solver = builder.parseConfig(json).create();
		solver.run();
	}
}
