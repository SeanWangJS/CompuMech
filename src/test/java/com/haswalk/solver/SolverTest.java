package com.haswalk.solver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.haswalk.util.io.IO;
import org.junit.Test;

import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

public class SolverTest {

	@Test
	public void test() throws IOException {
		String config = new String(
				Files.readAllBytes(Paths
						.get("E:/fvm/point-force-quad-mesh/config.json")));
		String mesh = new String(
				Files.readAllBytes(Paths
						.get("E:/fvm/point-force-tri-mesh/mesh.txt"))
		);

		IO.write(mesh, System.getProperty("user.dir") +  "/src/resources/mesh.txt");
		FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
		Solver solver = builder.parseConfig(config).create();
		solver.run();
	}


}
