package exe75.sort.colors;

class Solution {
    public void sortColors(int[] nums) {
        int zeroLastIndex = 0;
        int oneLastIndex = 0;
        int twoLastIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (twoLastIndex == oneLastIndex) {
                    twoLastIndex++;
                } else {
                    nums[twoLastIndex++] = 2;
                }
                if (oneLastIndex == zeroLastIndex) {
                    oneLastIndex++;
                } else {
                    nums[oneLastIndex++] = 1;
                }
                nums[zeroLastIndex++] = 0;
            } else if (nums[i] == 1) {
                if (twoLastIndex == oneLastIndex) {
                    twoLastIndex++;
                } else {
                    nums[twoLastIndex++] = 2;
                }
                nums[oneLastIndex++] = 1;
            } else {
                nums[twoLastIndex++] = 2;
            }
        }
    }
}