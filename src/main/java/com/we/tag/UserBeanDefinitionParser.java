package com.we.tag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
		//解析结果生成User实体类
        return User.class;  
    }  

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
		//从配置文件中获取值，并添加的User中
        String userName = element.getAttribute("name");  
        String email = element.getAttribute("email"); 
        int age = Integer.parseInt(element.getAttribute("age"));
  
        if (StringUtils.hasText(userName)) {
            bean.addPropertyValue("name", userName);  
        }  
        if (StringUtils.hasText(email)) {  
            bean.addPropertyValue("email", email);  
        }  
        bean.addPropertyValue("age", age);
    }  
 
}
