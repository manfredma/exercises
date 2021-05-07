package com.ilucky.guice;

public class MyServiceImpl implements MyService {

    public void service(String service) {
        System.out.println("===>" + service);
    }
}