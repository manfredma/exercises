public class HelloWorld {
    public native void hello();

    static {

        //设置查找路径为当前项目路径
        String newLib = System.getProperty("path.separator") + "/Users/maxingfang/IdeaProjects/exercises/jvm/jvm-native/";
        System.setProperty("java.library.path", System.getProperty("java.library.path") + newLib);
        System.getProperties().forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        //加载动态库的名称
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {
        new HelloWorld().hello();
    }
}
