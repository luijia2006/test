package com.we.basics.classloader;

public class ClassLoaderTest {

    /**
     * 可以看到ClassLoader的层次结构
     * BootStrapClassLoader,用于加载$JAVA_HOME/jre/lib下面的类库，虚拟机本地实现，
     * 开发者无法直接获取到启动类加载器的引用，不能通过引用进行操作
     * ExtClassLoader：扩展类加载器，在sun.misc.Launcher里作为一个内部类定义，
     * 加载$JAVA_HOME/jre/lib/ext下的类库，或者通过Djava.ext.deirs指定
     * AppClassLoader：应用程序类加载器，在sun.misc.Launcher里作为一个内部类定义，
     * 加载java环境变列CLASSPATH所指定的路径下的类库，可以通过System.getProperty("java.class.path")获取
     * 可以使用java -cp 路径 （进行覆盖）
     * @param args
     */
    public static void main(String... args) {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        System.err.println(loader);
        while (loader != null) {
            loader = loader.getParent();
            System.err.println(loader);
        }
    }
}
