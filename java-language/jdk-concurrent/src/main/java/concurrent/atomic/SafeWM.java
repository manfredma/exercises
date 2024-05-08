package concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Manfred since 2019/4/16
 */
public class SafeWM {
    class WMRange {
        final int upper;
        final int lower;

        WMRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }
    }

    final AtomicReference<WMRange> rf = new AtomicReference<WMRange>(
            new WMRange(0, 0)
    );

    void setUpper(int v) {
        WMRange nr;
        WMRange or = rf.get();
        do {
            // 检查参数合法性
            if (v < or.lower) {
                throw new IllegalArgumentException();
            }
            nr = new WMRange(v, or.lower);
        } while (!rf.compareAndSet(or, nr));
    }
}
