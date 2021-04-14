package manfred.end.jvm.off.heap.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Manfred since 2019/8/9
 */
public class GetUnsafeInstance {
    public static Unsafe getUnsafeInstance() {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
