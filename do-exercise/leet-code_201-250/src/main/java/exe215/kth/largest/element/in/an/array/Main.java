package exe215.kth.largest.element.in.an.array;

public class Main {
    public static void main(String[] args) {
        /*
         * <p>
         * Input: nums = [3,2,1,5,6,4], k = 2
         * Output: 5
         * Example 2:
         * <p>
         * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
         * Output: 4
         */
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
