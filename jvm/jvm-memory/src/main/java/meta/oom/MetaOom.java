package meta.oom;

import java.util.ArrayList;
import java.util.List;

public class MetaOom {
    public static void main(String[] args) throws Exception {
        // 将 classLoader 保存在方法内部变量里面，防止类卸载
        List<ClassLoader> classLoaderList = new ArrayList<>();
        while (true) {
            ClassLoader classLoader = new DiskClassLoader("jvm/jvm-classloader/clazz/");
            classLoaderList.add(classLoader);
            Class<?> c = classLoader.loadClass("manfred.end.clazz.loader.X");
        }
    }
}
