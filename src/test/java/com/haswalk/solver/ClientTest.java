package com.haswalk.solver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * Created by wangx on 2017/11/14.
 */
public class ClientTest {

    private final static String IP_ADDR="127.0.0.1";
    private final static int PORT = 12345;

    private Socket socket;

    private DecimalFormat format = new DecimalFormat(".00");

    public ClientTest connect() throws IOException {
        socket = new Socket(IP_ADDR, PORT);
        return this;
    }

    public void run(String config, String meshUri) throws IOException {
        File meshFile = new File(meshUri);
        DataInputStream inputFromFile = new DataInputStream(new FileInputStream(meshFile));
        DataOutputStream outputToServer = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());

        long fileLen = meshFile.length();
        long lenTillCurrent = 0;
        outputToServer.writeUTF(config);
        outputToServer.flush();

        byte[] buffer = new byte[1024];
        int readLen;
        while((readLen = inputFromFile.read(buffer)) != -1) {
            outputToServer.write(buffer, 0, readLen);
            lenTillCurrent += readLen;
            System.out.print("upload: " +  format.format(100.0 * lenTillCurrent / fileLen) + "%\r");
        }

        inputFromFile.close();
        outputToServer.close();
        inputFromServer.close();
        socket.close();

    }

    public static void main(String[] args) throws IOException {
        String config = new String(Files.readAllBytes(Paths.get("E:/fvm/8/config.json")));
        String meshUri = "E:/fvm/8/mesh.txt";
        new ClientTest().connect().run(config, meshUri);
    }
}














