package com.we.jetty.ws.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class TestServerMain {

    public static void main(String args[])
    {
        Server server = new Server(7778);

        /* webSocket的handler */
        WebSocketHandlerTest test = new WebSocketHandlerTest();
        ContextHandler context = new ContextHandler();
        /* 路径 */
        context.setContextPath("/test");
        context.setHandler(test);

        server.setHandler(context);
        try
        {
            /* 启动服务端 */
            server.start();
            server.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
