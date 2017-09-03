package com.haswalk.solver;

import com.haswalk.hasutil.IO;
import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {

        String config = new String(
                Files.readAllBytes(Paths
                        .get(System.getProperty("user.dir") + "/src/test/resources/config.json")));
        String mesh = new String(
                Files.readAllBytes(Paths
                        .get(System.getProperty("user.dir") + "/src/test/resources/mesh.txt"))
        );
        IO.write(mesh, System.getProperty("user.dir") +  "/src/resources/mesh.txt");
        FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
        Solver solver = builder.parseConfig(config).create();
        solver.run();
    }

}
