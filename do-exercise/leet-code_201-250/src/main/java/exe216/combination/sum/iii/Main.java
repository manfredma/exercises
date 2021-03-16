package exe216.combination.sum.iii;

public class Main {
    public static void main(String[] args) {
        /*
        Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * Example 3:
 * <p>
 * Input: k = 4, n = 1
 * Output: []
         */
        Solution solution = new Solution();
        System.out.println(solution.combinationSum3(3, 7));
        System.out.println(solution.combinationSum3(3, 9));
        System.out.println(solution.combinationSum3(4, 1));
    }
}
