package exe11.container.with.most.water;

class Solution {
    public int maxArea(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                int minH = Math.min(height[i], height[j]);
                int tmp = minH * (j - i);
                if (tmp > result) {
                    result = tmp;
                }
            }
        }
        return result;
    }
}