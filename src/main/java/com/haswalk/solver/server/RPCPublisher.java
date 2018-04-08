package com.haswalk.solver.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangx on 2017/12/3.
 */
public class RPCPublisher {

    public static void main(String[] args) throws IOException {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket socket = null;
        ServerSocket serverSocket = new ServerSocket(12345);

        while(true){
            try {
                System.out.println("waiting for connection...");
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + " has connected");
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                String serviceName = ois.readUTF();
                System.out.println("request service name: " + serviceName);
                String methodName = ois.readUTF();
                System.out.println("request method name: " + methodName);

                Class<?>[] parametersType = (Class<?>[]) ois.readObject();
                Object[] parameters = (Object[]) ois.readObject();
                Class<?> service = Class.forName(serviceName);
                Method method = service.getMethod(methodName, parametersType);
                Object result = method.invoke(service.getDeclaredConstructor().newInstance(), parameters);
                oos.writeObject(result);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                if(ois != null) {
                    ois.close();
                }
                if(oos != null) {
                    oos.close();
                }
                if(socket != null) {
                    socket.close();
                }
            }
        }
    }

}
