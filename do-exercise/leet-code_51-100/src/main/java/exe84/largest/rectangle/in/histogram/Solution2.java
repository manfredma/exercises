package exe84.largest.rectangle.in.histogram;

import java.util.Arrays;
import java.util.Stack;

class Solution2 {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        heights = Arrays.copyOf(heights, heights.length + 1);
        int i = 0;
        int maxArea = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int x = stack.pop();
                if (stack.isEmpty()) {
                    maxArea = Math.max(maxArea, i * heights[x]);
                } else {
                    maxArea = Math.max(maxArea, (i - stack.peek() - 1) * heights[x]);
                }
            }
        }
        return maxArea;
    }
}