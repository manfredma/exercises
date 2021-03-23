package manfred.end.clazz.loader;

public class CustomerClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.printf("Loading: %s%n", name);
        return super.loadClass(name);
    }
}
