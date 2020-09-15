package exe47.permutations.ii;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        doPermute(result, nums, 0);
        return result;
    }

    private void doPermute(List<List<Integer>> result, int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> single = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                single.add(nums[i]);
            }
            result.add(single);
        }

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[index]) {
                continue;
            }
            swap(nums, index, i);
            doPermute(result, nums, index + 1);
            swap(nums, index, i);
        }

    }

    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
}