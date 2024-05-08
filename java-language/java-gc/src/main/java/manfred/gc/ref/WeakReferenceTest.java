package manfred.gc.ref;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        WR wr = new WR(new Object(), new Object());
        for (int i = 0; i < 10000L; i++) {
            wr = new WR(new Object(), new Object());
        }
        System.out.println(wr);
        System.gc();
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(wr);
    }
}


class WR extends WeakReference<Object> {

    Object value;

    public WR(Object referent, Object value) {
        super(referent);
        this.value = value;
    }

    public String toString() {
        return "key: " + super.get() + ", value: " + value;
    }

}