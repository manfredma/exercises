package exe219.contains.duplicate.ii;

public class Main {
    public static void main(String[] args) {
        /*
         * Example 1:
         * <p>
         * Input: nums = [1,2,3,1], k = 3
         * Output: true
         * Example 2:
         * <p>
         * Input: nums = [1,0,1,1], k = 1
         * Output: true
         * Example 3:
         * <p>
         * Input: nums = [1,2,3,1,2,3], k = 2
         * Output: false
         */
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(solution.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(solution.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}
