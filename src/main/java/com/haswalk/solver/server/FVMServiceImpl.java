package com.haswalk.solver.server;

import com.haswalk.fvmservice.FVMService;
import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

import java.io.*;

/**
 * Created by wangx on 2017/12/4.
 */
public class FVMServiceImpl implements FVMService{
    @Override
    public String run(String params) {
        return "run: " + params;
    }

    @Override
    public void run(String config, byte[] meshByteStream) {
        saveMesh(meshByteStream);
        Solver solver = new FVM2DSolverBuilder().parseConfig(config).create();
        solver.run();
    }
    private void saveMesh(byte[] meshByteStream) {
        new File(System.getProperty("user.dir") + "/src/resources").mkdirs();
        BufferedOutputStream writeBuf = null;
        try {
            writeBuf = new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/src/resources/mesh.txt"));
            writeBuf.write(meshByteStream, 0, meshByteStream.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writeBuf != null) {
                try {
                    writeBuf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
