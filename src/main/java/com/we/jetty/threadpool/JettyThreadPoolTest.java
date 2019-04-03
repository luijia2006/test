package com.we.jetty.threadpool;

import jdk.internal.org.xml.sax.SAXException;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;
import java.util.Date;

public class JettyThreadPoolTest {
    private static final String contextPath = "/";
    private static final String warPath = "/war";
    private static final String jetty_home = ".";
    private Server server;
    private int serverPort;
    private int maxThreadPoolSize;
    private int maxInnerThreadPoolSize;

    private QueuedThreadPool threadPool;
    private ContextHandler  wsContextHandler = null;

    public static void main(String[] args) {
        JettyThreadPoolTest jettyServer = new JettyThreadPoolTest(10050, 1024, 10000,null);
        jettyServer.start();
    }

    public JettyThreadPoolTest(int serverPort, int maxThreadPoolSize, int maxInnerThreadPoolSize, ContextHandler wsContext) {
        this.wsContextHandler = wsContext;
        this.serverPort = serverPort;
        this.maxThreadPoolSize = maxThreadPoolSize;
        this.maxInnerThreadPoolSize = maxInnerThreadPoolSize;
        if(maxInnerThreadPoolSize > 0) {
            threadPool = new QueuedThreadPool(maxInnerThreadPoolSize);
            try {
                threadPool.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void start() {
        System.setProperty("jetty.home", jetty_home);
        try {
            DeploymentManager dm  = new DeploymentManager();
            ContextHandlerCollection contexts = new ContextHandlerCollection();
            dm.setContexts(contexts);

            QueuedThreadPool threadPool = new QueuedThreadPool(maxThreadPoolSize);
            server = new Server(threadPool);

            ServerConnector connector = new ServerConnector(server);
            connector.setPort(serverPort);
            server.addConnector(connector);
            server.addBean(dm);

            WebAppContext webapp = new WebAppContext("webapp","/web");
            webapp.setResourceBase("src/main/webapp");
            webapp.setDisplayName("web");
            webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
            webapp.setConfigurationDiscovered(true);
            webapp.setParentLoaderPriority(true);


            Handler[] hadnlers;
            ContextHandler resourceContext = createStaticResourceHandler();
            if(wsContextHandler==null){
                hadnlers = new Handler[]{resourceContext, webapp};
            }else{
                hadnlers = new Handler[]{resourceContext, webapp, wsContextHandler};
            }
            contexts.setHandlers(hadnlers);
            server.setHandler(contexts);
            server.setStopAtShutdown(true);
            server.setAttribute("org.eclipse.jetty.servlet.DefaultServlet.acceptRanges", true);
            server.setAttribute("org.eclipse.jetty.server.Request.maxFormContentSize", -1);

            server.start();
            System.out.println("handler--" + server.getHandler());
            System.out.println("connector--"+java.util.Arrays.toString(server.getConnectors()));
            System.out.println("threadPool--"+server.getThreadPool()+", minithread-number:"+server.getThreadPool().getThreads());
            System.out.println("Server started , current Time: " + new Date());

            server.join();
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static ContextHandler createStaticResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setResourceBase("webapp/WEB-INF/static");
        ContextHandler context = new ContextHandler();
        context.setContextPath("/res");
        context.setHandler(resourceHandler);
        return context;
    }

}
