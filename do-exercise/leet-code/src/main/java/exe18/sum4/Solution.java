package exe18.sum4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int targetM = target - nums[i] - nums[j];
                for (int k = j + 1, l = nums.length - 1; k < l; ) {
                    if (nums[k] + nums[l] == targetM) {
                        List<Integer> oneR = new ArrayList<>();
                        oneR.add(nums[i]);
                        oneR.add(nums[j]);
                        oneR.add(nums[k]);
                        oneR.add(nums[l]);
                        result.add(oneR);
                        while (k < l) {
                            k++;
                            if (nums[k] != nums[k - 1]) {
                                break;
                            }
                        }
                        while (l > k) {
                            l--;
                            if (nums[l] != nums[l + 1]) {
                                break;
                            }
                        }

                    } else if (nums[k] + nums[l] > targetM) {
                        l--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return result;
    }
}