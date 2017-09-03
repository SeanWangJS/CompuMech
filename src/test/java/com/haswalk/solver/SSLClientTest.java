package com.haswalk.solver;

import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by wangx on 2017/9/3.
 */
public class SSLClientTest {

    private final static String IP_ADDR = "39.108.150.164";
    private final static int PORT = 12345;

    private final static String TRUST_STORE = System.getProperty("user.dir")
            +"/src/test/resources/ssl_client_trust.jks";
    private final static String pass = "changeit";

    SSLSocket sslSocket = null;

    public SSLClientTest connect() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException {
        FileInputStream f_ssl_trust = new FileInputStream(TRUST_STORE);
        KeyStore ks = KeyStore.getInstance("jks");
        ks.load(f_ssl_trust, pass.toCharArray());
        f_ssl_trust.close();

        TrustManagerFactory tmFact = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmFact.init(ks);
        TrustManager[] tms = tmFact.getTrustManagers();
        SSLContext context = SSLContext.getInstance("SSL");
        context.init(null, tms, null);

        sslSocket =(SSLSocket) context.getSocketFactory().createSocket(IP_ADDR, PORT);
        System.out.println("connected to server");
        return this;
    }

    public void run(String config, String meshUri) throws IOException {
        DataOutputStream outputToServer = new DataOutputStream(sslSocket.getOutputStream());
        outputToServer.writeUTF(config);
        outputToServer.flush();
        File meshFile = new File(meshUri);
        outputToServer.writeLong(meshFile.length());
        outputToServer.flush();
        DataInputStream inputFromFile = new DataInputStream(new FileInputStream(meshFile));
        byte[] buffer = new byte[1024];
        int len;
        while((len = inputFromFile.read(buffer)) != -1) {
            outputToServer.write(buffer, 0, len);
            outputToServer.flush();
        }
        inputFromFile.close();
        outputToServer.close();
        sslSocket.close();
    }

    @Test
    public void test() throws IOException {
        String config = new String(Files.readAllBytes(Paths.get("E:/fvm/22/config.json")));
        String mesh = new String(Files.readAllBytes(Paths.get("E:/fvm/22/mesh.txt")));

        try {
            connect().run(config, "E:/fvm/22/mesh.txt");
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

    }

}
