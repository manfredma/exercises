package exe31.next.permutation;

class Solution {
    public void nextPermutation(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return;
        }
        boolean isBiggest = true;
        for (int i = nums.length -2; i >=0; i--) {
            if (nums[i] < nums[i+1]) {
                int s = i + 1;
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        s = j;
                    } else {
                        break;
                    }
                }
                int tmp = nums[i];
                nums[i] = nums[s];
                nums[s] = tmp;

                for (int j = i+1; j < (nums.length - (i + 1)) / 2 + (i + 1); j++) {
                    int tmp2 = nums[j];
                    nums[j] = nums[nums.length -1 - (j - (i + 1))];
                    nums[nums.length - 1 - (j - (i + 1))] = tmp2;
                }
                isBiggest = false;
                break;
            }
        }
        if (isBiggest) {
            for (int i = 0; i < nums.length / 2; i++) {
                int tmp = nums[i];
                nums[i] = nums[nums.length - 1 -i];
                nums[nums.length - 1 -i] = tmp;
            }
        }
    }
}