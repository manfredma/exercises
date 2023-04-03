package com.test;

public class B {

    private A a;

    public B() {
        new RuntimeException("调用B构造方法").printStackTrace(System.out);
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        new RuntimeException("调用B的方法setA").printStackTrace(System.out);
    }


    @Override
    public String toString() {
        return "B{" +
                "a=" + System.identityHashCode(a) +
                '}';
    }
}
