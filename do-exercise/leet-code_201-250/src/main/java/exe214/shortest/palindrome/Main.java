package exe214.shortest.palindrome;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestPalindrome("aacecaaa").equals("aaacecaaa"));

        System.out.println(solution.shortestPalindrome("aaaa").equals("aaaa"));
        System.out.println(solution.shortestPalindrome("abcd").equals("dcbabcd"));
        System.out.println(solution.shortestPalindrome("a").equals("a"));
        System.out.println(solution.shortestPalindrome("").equals(""));
    }
}
