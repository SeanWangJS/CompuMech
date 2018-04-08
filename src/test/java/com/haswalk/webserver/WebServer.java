package com.haswalk.webserver;

import com.haswalk.solver.Solver;
import com.haswalk.solver.fvm2d.FVM2DSolverBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * Created by wangx on 2017/11/14.
 */
public class WebServer {

    private ServerSocket serverSocket = null;
    private final static int PORT = 12345;

    public void run() throws IOException {
        serverSocket = new ServerSocket(PORT);
        while(true) {
            System.out.println("waiting for connection...");
            Socket socket = serverSocket.accept();
            invoke(socket);
        }
    }

    private void invoke(Socket socket) throws IOException {
        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
        String config = inputFromClient.readUTF();

        long fileLen = inputFromClient.readLong();
        boolean mkdirs = new File(System.getProperty("user.dir") + "/src/resources").mkdirs();

        DataOutputStream outputToFile = new DataOutputStream(
                new FileOutputStream(
                        new File(
                                System.getProperty("user.dir") + "/src/resources/mesh.txt")));

        byte[] buffer = new byte[1024];
        int len;
        long current = 0L;
        DecimalFormat format = new DecimalFormat(".00");
        while((len = inputFromClient.read(buffer)) != -1) {
            outputToFile.write(buffer,0, len);
            current += len;
            double progress = 100.0 * current / fileLen;
            System.out.print("Info: progress " + format.format(progress) + "%\r");
        }
        System.out.println();
        System.out.println("config and mesh read complete!\nsolver running...");
        solve(config);
        System.out.println();
        System.out.println("solve complete!");

        inputFromClient.close();
        outputToFile.close();
        socket.close();
    }

    private void solve(String config) {
        FVM2DSolverBuilder builder = new FVM2DSolverBuilder();
        Solver solver = builder.parseConfig(config).create();
        solver.run();
    }

    public static void main(String[] args) throws IOException {
        new WebServer().run();
    }
}





















