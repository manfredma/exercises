package exe26.remove.duplicates.from.sorted.array;

class Solution {
    public int removeDuplicates(int[] nums) {
        int result = 0;
        if (null == nums || nums.length == 0) {
            return result;
        }
        int current = nums[0];
        result++;
        for (int i = 1; i < nums.length; i++) {
            if (current == nums[i]) {
                continue;
            }
            current = nums[i];
            nums[result++] = current;
        }
        return result;
    }
}