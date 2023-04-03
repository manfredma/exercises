package com.test;

public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        new RuntimeException().printStackTrace(System.out);
    }


    @Override
    public String toString() {
        return "B{" +
                "a=" + System.identityHashCode(a) +
                '}';
    }
}
