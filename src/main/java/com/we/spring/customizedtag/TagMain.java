package com.we.spring.customizedtag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TagMain {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("config/tag.xml");
        User user = (User) ac.getBean("testBean");
        System.out.println(user.getName());
	}
 
}