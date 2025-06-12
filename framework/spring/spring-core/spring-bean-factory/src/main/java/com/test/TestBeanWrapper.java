package com.test;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

public class TestBeanWrapper {
    public static void main(String[] args) {
        BeanWrapper bw = new BeanWrapperImpl(User.class);
        bw.setPropertyValue("name", "maxingfang");
        bw.setPropertyValue("age", 25);
        bw.setPropertyValue("hobbies", Arrays.asList("2", "3"));
        User user = (User) bw.getWrappedInstance();
        System.out.println(user);
    }
}
