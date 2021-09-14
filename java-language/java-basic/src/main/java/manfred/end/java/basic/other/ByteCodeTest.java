package manfred.end.java.basic.other;

public class ByteCodeTest {
    private void a() {

    }

    public final void x() {
        a();
    }


    public static void main(String[] args) {
        ByteCodeTest byteCodeTest = new ByteCodeTest();
        byteCodeTest.x();
        Runnable r = () -> System.out.println("hello");
        r.run();
    }
}
