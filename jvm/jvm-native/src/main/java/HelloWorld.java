public class HelloWorld {
    public native void hello();

    static {
        //加载动态库的名称
        System.loadLibrary("hello");
    }

    public static void main(String[] args) {
        new HelloWorld().hello();
    }
}
