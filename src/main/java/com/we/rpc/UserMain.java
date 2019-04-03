package com.we.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class UserMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                String apiClassName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class[] parameterTypes = (Class[]) objectInputStream.readObject();
                Object[] argsMethod = (Object[]) objectInputStream.readObject();

                Class clazz = null;
                if (apiClassName.equals(UserService.class.getName())){
                    clazz = UserServiceImpl.class;
                }
                ObjectOutputStream objectOutputStream = null;
                if (null != clazz) {
                    Method method = clazz.getMethod(methodName, parameterTypes);
                    Object invoke = method.invoke(clazz.newInstance(), argsMethod);
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(invoke);
                    objectOutputStream.flush();
                }
                // 关闭资源
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
                if (null != objectOutputStream) {
                    objectOutputStream.close();
                }
                if (null != socket) {
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}