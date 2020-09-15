package exe80.remove.duplicates.from.sorted.array.ii;

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 1;
        int pre = nums[0];
        int numOfCurrent = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] == pre) {
                numOfCurrent++;
                if (numOfCurrent <= 2) {
                    nums[i++] = pre;
                }
            } else {
                pre = nums[j];
                nums[i++] = pre;
                numOfCurrent = 1;
            }
        }
        return i;
    }
}