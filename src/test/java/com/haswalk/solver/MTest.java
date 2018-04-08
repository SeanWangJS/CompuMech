package com.haswalk.solver;

import com.haswalk.solver.server.FVMServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by wangx on 2017/12/4.
 */
public class MTest {
    @Test
    public void test() throws IOException {
        byte[] meshByteStream = new String(Files.readAllBytes(Paths.get("E:/fvm/22/mesh.txt"))).getBytes();
        new FVMServiceImpl().run(null, meshByteStream);
    }
}
