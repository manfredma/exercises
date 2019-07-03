package exe45.jump.game.ii;

class Solution {
    public int jump(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return 0;
        }

        int[] jump = new int[nums.length];
        for (int i = 1; i < jump.length; i++) {
            jump[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j <= nums[i] + i && j < nums.length; j++) {
                if (jump[j] > jump[i] + 1) {
                    jump[j] = jump[i] + 1;
                }
            }
        }
        return jump[nums.length - 1];
    }
}