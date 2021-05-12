package manfred.end.jvm.method.invoke;

import org.junit.Test;

public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    @Test
    public void test() {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

    }


    public void sayHello(Human guy) {
        System.out.println("Hello guy");
    }

    public void sayHello(Man guy) {
        System.out.println("Hello man");
    }

    public void sayHello(Woman guy) {
        System.out.println("Hello woman");
    }
}