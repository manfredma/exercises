package manfred.end.gc;

import java.io.IOException;

/**
 * @author manfred on 2021/5/9.
 */
public class Boot {
    public static void main(String[] args) throws IOException {
        System.gc();
        System.in.read();
    }
}
