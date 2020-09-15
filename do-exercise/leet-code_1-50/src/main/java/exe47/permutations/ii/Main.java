/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */

package exe47.permutations.ii;

/**
 * @author Manfred since 2019/7/3
 */
public class Main {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.permuteUnique(new int[]{1, 1, 2}));

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.permuteUnique(new int[]{2, 2, 1, 1}));
        System.out.println(solution2.permuteUnique(new int[]{1, 1, 2}));
    }
}
