package com.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        A a = fa.getBean("a", A.class);
        B b = fa.getBean("b", B.class);

        System.out.println(System.identityHashCode(a) + ": " + a);
        System.out.println(System.identityHashCode(b) + ": " + b);

    }
}