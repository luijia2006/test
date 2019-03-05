package com.we.jetty.ws.client;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * 相应的webSocket一个测试小demo就完成了，
 * 虽然相应的例子比较少，
 * 官方文档又只有个别代码片段，
 * 新手入门比较难，但总算搭建完成，
 * 具体啊有其他的扩展还得去官方api查看了
 */
public class TestClientMain {
    public static void main(String args[])
    {
        String destUri = "ws://127.0.0.1:7778/test/";
        if (args.length > 0)
        {
            destUri = args[0];
        }
        WebSocketClient client = new WebSocketClient();
        SimpleEchoSocket socket = new SimpleEchoSocket();
        try
        {
            client.start();
            URI echoUri = new URI(destUri);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setSubProtocols("c");
            request.setHeader("index", "3");
            /* 使用相应的webSocket进行连接 */
            client.connect(socket, echoUri, request);

            System.out.printf("Connecting to : %s%n", echoUri);
            socket.awaitClose(1000, TimeUnit.SECONDS);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            try
            {
                client.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
