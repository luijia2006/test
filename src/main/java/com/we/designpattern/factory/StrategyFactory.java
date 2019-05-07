package com.we.designpattern.factory;

import com.we.designpattern.factory.annotation.Pay;
import com.we.designpattern.factory.channel.Strategy;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 懒汉模式没有线程安全问题
 * 饿汉模式有线程安全问题
 * 需要双重校验锁
 *
 *
 */
public class StrategyFactory {
    public static Map<Integer, String> map = new HashMap<>();
    private StrategyFactory(){}
    private volatile static StrategyFactory factory;
    public static StrategyFactory getInstance(){
        if (factory == null) {
            synchronized (StrategyFactory.class) {
                if (factory == null) {
                    factory = new StrategyFactory();
                }
            }
        }
        return factory;
    }

    static {
        Reflections reflections = new Reflections("com.we.chanel.impl");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Pay.class);
        for (Class clazz : classSet){
            Pay t=(Pay) clazz.getAnnotation(Pay.class);
            map.put(t.channelId(), clazz.getCanonicalName());
        }
    }

    public Strategy create(int channelId){
        String className = map.get(channelId);
        try {
            Class<?> aClass = Class.forName(className);
            return (Strategy)aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
