package exe216.combination.sum.iii;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * <p>
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination
 * twice, and the combinations may be returned in any order.
 * <p>
 * Example 1:
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
 * Explanation: There are no valid combinations. [1,2,1] is not valid because 1 is used twice.
 */
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k > n || n < 1 || k < 0 || n > 45 || k > 9) {
            return new ArrayList<>();
        }
        return combinationSum3(k, n, 9);
    }

    private List<List<Integer>> combinationSum3(int k, int n, int can) {

        List<List<Integer>> result = new ArrayList<>();
        if (k > n || n < 0 || k < 0 || can < 1) {
            return result;
        }

        if (k == 1) {
            if (n <= can) {
                List<Integer> x = new ArrayList<>();
                x.add(n);
                result.add(x);
            }
            return result;
        }

        // 递归解决！
        // 当前值参与计算
        List<List<Integer>> x = combinationSum3(k - 1, n - can, can - 1);
        for (List<Integer> integers : x) {
            integers.add(can);
            result.add(integers);
        }
        // 当前值不参与计算
        result.addAll(combinationSum3(k, n, can - 1));
        return result;
    }
}