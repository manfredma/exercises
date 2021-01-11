package manfred.end.java.basic.other;

import org.junit.Test;

import java.util.UUID;

/**
 * @author Manfred since 2019/7/2
 */
public class UuidTest {

    @Test
    public void testCreate() {
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());
        System.out.println(createGUID());

    }

    private static String createGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
