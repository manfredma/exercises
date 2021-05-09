package manfred.end.plugin;

import java.io.ByteArrayOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.InputStream;

public class ClassReloader extends ClassLoader {

    private String classPath;

    public ClassReloader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }

    public ClassReloader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getData(String className) {
        String path = classPath + getFileName(className);
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = is.read(buffer)) != -1) {
                stream.write(buffer, 0, num);
            }
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            String path = "jvm/jvm-classloader/clazz/";
            ClassReloader reloader = new ClassReloader(path);
            Class r = reloader.findClass("manfred.end.clazz.loader.X");
            System.out.println((r.newInstance()));
            ClassReloader reloader1 = new ClassReloader(path);
            // 如果 r1 继续通过 reloader 进行加载则会出现重复类的错误
            // java.lang.LinkageError: loader (instance of  manfred/end/plugin/ClassReloader): attempted  duplicate class definition for name: "manfred/end/clazz/loader/X"
            Class r1 = reloader1.findClass("manfred.end.clazz.loader.X");
            System.out.println((r1.newInstance()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取要加载 的class文件名
    private static String getFileName(String name) {
        // 不考虑特殊的类格式
        return name.replace(".", "/") + ".class";
    }
}