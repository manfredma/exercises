package exe1108.defanging.an.ip.address;

/**
 * @author manfred on 2021/5/9.
 */
public class Main {
    public static void main(String[] args) {
        //  * Input: address = "1.1.1.1"
        // * Output: "1[.]1[.]1[.]1"
        // * Example 2:
        // *
        // * Input: address = "255.100.50.0"
        // * Output: "255[.]100[.]50[.]0"
        Solution solution = new Solution();
        System.out.println(solution.defangIPaddr("1.1.1.1"));
        System.out.println(solution.defangIPaddr("255.100.50.0"));
    }
}
