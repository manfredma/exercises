package exe209.minimum.size.subarray.sum;

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int subSum = 0;
        int[] subLen = new int[nums.length];
        for (int i = 0; i < len; i++) {
            subSum += nums[i];
            if (subSum >= s) {
                subLen[0] = i + 1;
                break;
            }
        }
        if (subSum < s) {
            return 0;
        }
        if (subLen[0] == 1) {
            return 1;
        }

        int result = subLen[0];
        for (int i = 1; i < len; i++) {
            // 减去前一个元素的值
            subSum -= nums[i - 1];
            if (subSum >= s) {
                subLen[i] = subLen[i - 1] - 1;
                if (subLen[i] == 1) {
                    return 1;
                }
                result = Math.min(result, subLen[i]);
                continue;
            }

            for (int j = subLen[i - 1] + i - 1; j < len; j++) {
                subSum += nums[j];
                if (subSum >= s) {
                    subLen[i] = j - i + 1;
                    result = Math.min(result, subLen[i]);
                    break;
                }
            }

            if (subSum < s) {
                break;
            }
        }
        return result;
    }
}