package manfred.end.jvm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class MethodInterceptor {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String r = new ByteBuddy()
                .subclass(Foo.class)
                .method(named("sayHelloFoo")
                        .and(isDeclaredBy(Foo.class)
                                .and(returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(MethodInterceptor.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .sayHelloFoo();
        System.out.println(r);
    }

    public static class Foo {
        public String sayHelloFoo() {
            return "Hello in Foo!";
        }
    }

    public static class Bar {
        @BindingPriority(3)
        public static String sayHelloBar() {
            return "Holla in Bar!";
        }

        @BindingPriority(4)
        public static String sayBar() {
            return "bar！！！";
        }
    }
}


