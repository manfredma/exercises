package manfred.end.thread.local;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/**
 * @author manfred on 2021/5/9.
 */
public class Boot {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 使用反射读取 threadLocal 中的值
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("xxx");

        Field field = Thread.class.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object map = field.get(Thread.currentThread());
        Class m = map.getClass();
        Field t = m.getDeclaredField("table");
        t.setAccessible(true);
        WeakReference[] o = (WeakReference[]) t.get(map);
        for (WeakReference o1 : o) {
            if (o1 != null) {
                Class ec = o1.getClass();
                Field ecF = ec.getDeclaredField("value");
                ecF.setAccessible(true);
                System.out.println("value: " + ecF.get(o1));
            }
        }

    }
}
