package manfred.end.clazz.loader;

public class Boot {
    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println("boot class path -> ");
        for (String s : System.getProperty("sun.boot.class.path").split(":")) {
            System.out.println("\t" + s);
        }

        System.out.println("ext class path -> ");
        for (String s : System.getProperty("java.ext.dirs").split(":")) {
            System.out.println("\t" + s);
        }

        System.out.println("app class path -> ");
        for (String s : System.getProperty("java.class.path").split(":")) {
            System.out.println("\t" + s);
        }

        System.out.println("查看自定义 classLoader 的双亲委派模型 -> ");
        ClassLoader classLoader = new CustomerClassLoader();
        while (classLoader != null) {
            System.out.println("\t" + classLoader.getClass() + " -> " + classLoader);
            classLoader = classLoader.getParent();
        }

        System.out.println("查看自定义类的类加载器 ->");
        System.out.println("\t X.class 的类加载器是 " + X.class.getClassLoader());

        classLoader = new CustomerClassLoader();
        classLoader.loadClass("manfred.end.clazz.loader.X");
        classLoader.loadClass("xxx");

    }
}
