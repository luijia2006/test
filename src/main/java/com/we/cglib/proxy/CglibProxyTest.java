package com.we.cglib.proxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibProxyTest {

    public static void main(String[] args) {
        // 生成class类的路径
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D://tmp");
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(ConcreteClassNoInterface.class);
        enhancer.setCallback(new ConcreteClassInterceptor());
        ConcreteClassNoInterface ccni=(ConcreteClassNoInterface)enhancer.create();
        ccni.getConcreteMethodA("shensy");
        ccni.getConcreteMethodB(0);
    }

}
