package exe46.permutations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> nums2 = new ArrayList<>();
        for (int num : nums) {
            nums2.add(num);
        }
        return permute(nums2);
    }

    private List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums.size() == 1) {
            List<Integer> integers = new ArrayList<>();
            integers.add(nums.get(0));
            lists.add(integers);
            return lists;
        }
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> numsS = new ArrayList<>(nums);
            numsS.remove(nums.get(i));
            List<List<Integer>> s = permute(numsS);
            for (List<Integer> integers : s) {
                List<Integer> integers2 = new ArrayList<>();
                integers2.add(nums.get(i));
                integers2.addAll(integers);
                lists.add(integers2);
            }
        }
        return lists;
    }
}