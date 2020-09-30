package manfred.end.jvm.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class RedefineMethod {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(Foo.class)
                .method(named("sayHelloFoo"))
                .intercept(FixedValue.value("Hello Foo Redefined"))
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        Foo f = new Foo();

        System.out.println(f.sayHelloFoo());
    }

    public static class Foo {
        public String sayHelloFoo() {
            return "Hello in Foo!";
        }
    }

}


