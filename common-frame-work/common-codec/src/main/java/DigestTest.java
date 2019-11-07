import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author manfred
 * @since 2019-11-06 下午4:18
 */
public class DigestTest {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("hello"));
    }
}
