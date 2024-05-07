package exe55.jump.game;

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            int maxI = i + nums[i];
            if (maxI > max) {
                max = maxI;
            }
            if (max >= nums.length - 1) {
                return true;
            }
            if (max == i) {
                return false;
            }
        }
        return false;
    }
}