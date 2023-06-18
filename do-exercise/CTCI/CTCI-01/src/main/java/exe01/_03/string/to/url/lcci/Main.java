package exe01._03.string.to.url.lcci;

/**
 * <a href="https://leetcode.cn/problems/string-to-url-lcci/?envType=study-plan-v2&envId=cracking-the-coding-interview">leetcode 题目</a>
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.replaceSpaces("Mr John Smith    ", 13));
        System.out.println(solution.replaceSpaces("               ", 5));

        SolutionV2 solutionV2 = new SolutionV2();
        System.out.println(solutionV2.replaceSpaces("Mr John Smith    ", 13));
        System.out.println(solutionV2.replaceSpaces("               ", 5));
        System.out.println(solutionV2.replaceSpaces("ds sdfs afs sdfa dfssf asdf             ", 27));
        System.out.println(solution.replaceSpaces("ds sdfs afs sdfa dfssf asdf             ", 27));
    }
}
