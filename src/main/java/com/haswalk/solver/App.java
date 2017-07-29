package com.haswalk.solver;

import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        System.out.println(System.getProperty("user.dir"));
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("E:/fvm/20-tri/config.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String mesh = null;
        try {
            mesh = new String(Files.readAllBytes(Paths.get("E:/fvm/20-tri/mesh.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FVM2DSolverBuilder builder = new FVM2DSolverBuilder().mesh("resources", mesh);
        Solver solver = builder.parseConfig(json).create();
        solver.run();
    }

}
