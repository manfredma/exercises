package manfred.agent.basic;

import java.lang.reflect.Method;

public class Boot {
    public static void main(String[] args) {
        new Throwable().printStackTrace(System.out);
        System.out.println("hello, world!");

        classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
        Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.X");
        System.out.println("\t" + c + " 的类加载器是 " + c.getClassLoader());
        Object o = c.newInstance();
        Method m = c.getMethod("sayHello");
        m.invoke(o);
    }
}
