package exe27.remove.element;

class Solution {
    public int removeElement(int[] nums, int val) {
        int result = 0;
        if (null == nums || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            if (val == nums[i]) {
                continue;
            }
            nums[result++] = nums[i];
        }
        return result;
    }
}