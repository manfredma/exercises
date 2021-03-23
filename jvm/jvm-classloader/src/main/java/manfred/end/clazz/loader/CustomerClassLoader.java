package manfred.end.clazz.loader;

public class CustomerClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("尝试加载-" + name);
        return super.findClass(name);
    }
}
