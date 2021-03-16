package exe220.contains.duplicate.iii;

public class Main {
    public static void main(String[] args) {
        /*
         * Input: nums = [1,2,3,1], k = 3, t = 0
         * Output: true
         * Example 2:
         *
         * Input: nums = [1,0,1,1], k = 1, t = 2
         * Output: true
         * Example 3:
         *
         * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
         * Output: false
         *
         * [2147483640,2147483641] 1 100
         * [2147483647,-1,2147483647] 1 2147483647
         */
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{2147483647,-1,2147483647}, 1, 2147483647));
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{2147483640, 2147483641}, 1, 100));
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
