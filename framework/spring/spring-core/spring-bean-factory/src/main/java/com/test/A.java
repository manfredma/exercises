package com.test;

public class A {

    private B b;

    public A () {
        new RuntimeException("调用A构造方法").printStackTrace(System.out);
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        new RuntimeException("调用A的方法setB").printStackTrace(System.out);
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + System.identityHashCode(b) +
                '}';
    }
}
