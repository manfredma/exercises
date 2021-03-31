package address;

public class A {
    private long a;        // not initialized value

    public A() {
        this.a = 1;        // initialization
    }

    public long a() {
        return this.a;
    }
}
