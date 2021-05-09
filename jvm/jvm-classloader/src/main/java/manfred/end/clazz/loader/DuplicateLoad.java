package manfred.end.clazz.loader;

public class DuplicateLoad {
    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
        Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.X");
        Class<?> c2 = classLoader.loadClass("manfred.end.clazz.loader.X");
    }
}
