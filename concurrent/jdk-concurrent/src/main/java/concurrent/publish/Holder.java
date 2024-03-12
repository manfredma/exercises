package concurrent.publish;

public class Holder {
    public int n;

    public int a;

    public int j;

    public Holder(int n) {
        this.n = n;
        this.a = n;
        this.j = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false.");
        }
    }
}