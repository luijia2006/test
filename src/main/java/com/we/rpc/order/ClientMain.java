package com.we.rpc.order;

import com.we.rpc.product.Product;
import com.we.rpc.product.ProductService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        // 获取订单详情
        OrderService orderService = new OrderService();
        Order order = orderService.selectOrderById(1L);
        System.out.println("订单信息：" + order);
        // 根据商品id获取商品
        ProductService productService = (ProductService) rpcHandle(ProductService.class, "127.0.0.1", 8880);
        Product product = productService.selectProductById(order.getProductId());
        System.out.println("商品信息：" + product);
        // 根据用户id获取用户
        /*UserService userService = (UserService) rpcHandle(UserService.class, "127.0.0.1", 9999);
        User user = userService.selectUserById(order.getUserId());
        System.out.println("用户信息：" + user);
        */
    }

    /**
     * RPC远程调用实现
     */
    public static Object rpcHandle(final Class clazz, final String host, final int port) {
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        // 想要调用远程类的某个方法，必须要知道类名称，方法名称，参数类型，参数
                        String apiClassName = clazz.getName();
                        String methodName = method.getName();
                        Class[] parameterTypes = method.getParameterTypes();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeUTF(apiClassName);
                        objectOutputStream.writeUTF(methodName);
                        objectOutputStream.writeObject(parameterTypes);
                        objectOutputStream.writeObject(args);
                        objectOutputStream.flush();

                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        Object object = objectInputStream.readObject();
                        if (null != objectInputStream ){
                            objectInputStream.close();
                        }
                        if (null != objectOutputStream){
                            objectOutputStream.close();
                        }
                        if (null != socket){
                            socket.close();
                        }
                        return object;
                    }
                }
        );
    }
}