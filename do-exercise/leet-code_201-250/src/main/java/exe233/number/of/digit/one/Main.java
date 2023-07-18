package exe233.number.of.digit.one;

/**
 * @author manfred on 2023/6/7.
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countDigitOne(13));
        System.out.println(solution.countDigitOne(0));
        System.out.println(solution.countDigitOne(12));
        System.out.println(solution.countDigitOne(20));

        SolutionV2 solutionV2 = new SolutionV2();
        System.out.println(solutionV2.countDigitOne(13));
        System.out.println(solutionV2.countDigitOne(0));
        System.out.println(solutionV2.countDigitOne(12));
        System.out.println(solutionV2.countDigitOne(20));
    }
}
