package manfred.end.clazz.loader;

import java.lang.reflect.Method;

public class Boot {
    public static void main(String[] args) throws Exception {
        String pathSeparator = System.getProperty("path.separator");
        System.out.println("boot class path -> ");
        for (String s : System.getProperty("sun.boot.class.path").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("ext class path -> ");
        for (String s : System.getProperty("java.ext.dirs").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("app class path -> ");
        for (String s : System.getProperty("java.class.path").split(pathSeparator)) {
            System.out.println("\t" + s);
        }
        System.out.println("查看自定义 classLoader 的双亲委派模型 -> ");
        ClassLoader classLoader = new CustomerClassLoader();
        while (classLoader != null) {
            System.out.println("\t" + classLoader.getClass() + " -> " + classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println("使用自定义类的类加载器 ->");
        classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
        Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.X");
        System.out.println("\t" + c + " 的类加载器是 " + c.getClassLoader());
        Object o = c.newInstance();
        Method m = c.getMethod("sayHello");
        m.invoke(o);
    }
}
