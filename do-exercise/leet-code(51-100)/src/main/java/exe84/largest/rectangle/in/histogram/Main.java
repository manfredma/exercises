/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.



Example:

Input: [2,1,5,6,2,3]
Output: 10
 */
package exe84.largest.rectangle.in.histogram;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestRectangleArea(new int[]{1, 1}));
        System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
