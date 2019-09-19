/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

package exe46.permutations;

/**
 * @author Manfred since 2019/7/3
 */
public class Main {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.permute(new int[]{1, 2, 3}));
    }
}
