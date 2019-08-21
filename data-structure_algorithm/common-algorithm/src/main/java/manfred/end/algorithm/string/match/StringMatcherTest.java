package manfred.end.algorithm.string.match;

import org.junit.Test;

/**
 * @author Manfred since 2019/8/21
 */
public class StringMatcherTest {

    private int loop = 1_000_000;

    private boolean output = true;

    @Test
    public void testBF() {
        BF bf = new BF();
        if (output) {
            System.out.println("bf.match(\"hello\", \"h\")=" + bf.match("hello", "h"));
            System.out.println("bf.match(\"hello\", \"ll\")=" + bf.match("hello", "ll"));
            System.out.println("bf.match(\"hello\", \"a\")" + bf.match("hello", "a"));
        }
        for (int i = 0; i < loop; i++) {
            bf.match("hello", "h");
            bf.match("hello", "ll");
            bf.match("hello", "a");
        }
    }

    @Test
    public void testRK() {
        RK rk = new RK();
        if (output) {
            System.out.println("rk.match(\"hello\", \"ll\")=" + rk.match("hello", "ll"));
            System.out.println("rk.match(\"hello\", \"h\")=" + rk.match("hello", "h"));
            System.out.println("rk.match(\"hello\", \"a\")" + rk.match("hello", "a"));
        }
        for (int i = 0; i < loop; i++) {
            rk.match("hello", "h");
            rk.match("hello", "ll");
            rk.match("hello", "a");
        }
    }

    @Test
    public void testBF2() {
        BF bf = new BF();
        if (output) {
            System.out.println("bf.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"hriuqwpo\")=" + bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpo"));
            System.out.println("bf.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"sdgas\")=" + bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "sdgas"));
            System.out.println("bf.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"faklasdf\")=" + bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdf"));
        }
        for (int i = 0; i < loop; i++) {
            bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpo");
            bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "fjkals");
            bf.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdf");
        }
    }

    @Test
    public void testRK2() {
        RK rk = new RK();
        if (output) {
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"hriuqwpo\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpo"));
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"sdgas\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "sdgas"));
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"faklasdf\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdf"));
        }
        for (int i = 0; i < loop; i++) {
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpo");
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "fjkals");
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdf");
        }
    }

    @Test
    public void testKMP2() {
        KMP rk = new KMP();
        if (output) {
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"hriuqwpo\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpo"));
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"sdgas\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "sdgas"));
            System.out.println("rk.match(\"helakljgfqalwsdjkgqwioegnksdgasjfgklo\", \"faklasdf\")=" + rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdf"));
        }
        for (int i = 0; i < loop; i++) {
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "hriuqwpofasdfas");
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "fjkals");
            rk.match("helakljgfqalwsdjkgqwioegnksdgasjfgklo", "faklasdfrqwee");
        }
    }
}
