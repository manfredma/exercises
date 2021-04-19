package manfred.agent.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Boot {
    public static void main(String[] args) throws Exception {
        // new Throwable().printStackTrace(System.out);
        System.out.println("hello, world!");
        loadAndExeX();
        loadAndExeX();
    }

    private static void loadAndExeX() throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader2 = new DiskClassLoader("clazz/");
        Class<?> c2 = classLoader2.loadClass("manfred.end.clazz.loader.X");
        System.out.println("\t" + c2 + " 的类加载器是 " + c2.getClassLoader());
        Object o2 = c2.newInstance();
        Method m2 = c2.getMethod("sayHello");
        m2.invoke(o2);
    }
}
