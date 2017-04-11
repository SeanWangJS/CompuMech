package com.haswalk.solver.fvm2d.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

public class SolverTest {
	private String json = null;
	@Before
	public void before() throws IOException{
//		Paths.get(System.getProperty("user.dir"), "src/com/haswalk/solver/fvm2d/test/resource/config.json");
//		json = new String(Files.readAllBytes(Paths.get("C:/Users/wangx/OneDrive/workspace/neon/solver/src/com/haswalk/solver/fvm2d/test/resource/config.json")));
		json = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir"), "src/com/haswalk/solver/fvm2d/test/resource/config.json")));
	}
	@Test
	public void testSolve(){
		FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
		Solver solver = builder.parseConfig(json).create();
		solver.run();
	}
}
