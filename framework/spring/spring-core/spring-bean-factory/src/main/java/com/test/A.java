package com.test;

public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        new RuntimeException().printStackTrace(System.out);
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + System.identityHashCode(b) +
                '}';
    }
}
