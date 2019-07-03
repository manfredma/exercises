import java.util.UUID;

/**
 * @author Manfred since 2019/7/2
 */
public class UuidCreator {
    public static void main(String[] args) {
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
