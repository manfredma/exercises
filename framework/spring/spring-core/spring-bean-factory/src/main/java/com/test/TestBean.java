package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class TestBean {

    @Value("${showMsg}")
    public String showMsg;

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public static void main(String[] args) {
        BeanFactory fa = new ClassPathXmlApplicationContext("beans.xml");
        TestBean bean = fa.getBean("testBean", TestBean.class);
        System.out.println(bean.getShowMsg());
    }
}