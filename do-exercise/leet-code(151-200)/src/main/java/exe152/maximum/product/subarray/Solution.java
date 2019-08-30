package exe152.maximum.product.subarray;

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int lastZeroIndex = -1;
        int result = Integer.MIN_VALUE;
        int[] product = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (result < 0) {
                    result = 0;
                }
                if (i - 1 >= 0) {
                    if (product[i - 1] > 0) {
                        result = Math.max(result, product[i - 1]);
                    } else {
                        if (product[i - 1] < 0 && i - lastZeroIndex > 2) {

                            int rightMax = product[i - 1];
                            int leftMax = product[i - 1];
                            for (int j = lastZeroIndex + 1; j < i; j++) {
                                rightMax = rightMax < 0 ? rightMax / nums[j] : rightMax;
                                leftMax = leftMax < 0 ? leftMax / nums[i - 1 - (j - lastZeroIndex - 1)] : leftMax;
                            }
                            result = Math.max(result, Math.max(leftMax, rightMax));

                        }
                    }
                }
                lastZeroIndex = i;
                continue;
            }
            product[i] = i == 0 || nums[i - 1] == 0 ? nums[i] : nums[i] * product[i - 1];
        }

        if (product[nums.length - 1] != 0) {
            if (product[nums.length - 1] > 0) {
                result = Math.max(result, product[nums.length - 1]);
            } else {
                if (product[nums.length - 1] < 0 && nums.length - lastZeroIndex > 2) {

                    int rightMax = product[nums.length - 1];
                    int leftMax = product[nums.length - 1];
                    for (int j = lastZeroIndex + 1; j < nums.length; j++) {
                        rightMax = rightMax < 0 ? rightMax / nums[j] : rightMax;
                        leftMax = leftMax < 0 ? leftMax / nums[nums.length - 1 - (j - lastZeroIndex - 1)] : leftMax;
                    }
                    result = Math.max(result, Math.max(leftMax, rightMax));

                }
            }
        }

        return result;
    }
}