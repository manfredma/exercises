package exe01._02.check.permutation.lcci;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.CheckPermutation("aa", "ab"));
        System.out.println(solution.CheckPermutation("abc", "bca"));
        System.out.println(solution.CheckPermutation("aa", "aa"));
    }
}
