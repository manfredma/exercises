package exe15.sum3;

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Set<Integer>> repeated = new HashMap<>();
        Map<Integer, Integer> numsSet = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numsSet.put(nums[i], i);
        }

        Set<Integer> checkedFirst = new HashSet<>(nums.length);

        for (int i = 0; i < nums.length - 2; i++) {
            if (checkedFirst.contains(nums[i])) {
                continue;
            }
            Set<Integer> checkedSecond = new HashSet<>(nums.length);
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (checkedSecond.contains(nums[j])) {
                    continue;
                }
                Integer pos = numsSet.get(-nums[i] - nums[j]);
                if (null != pos && pos > j) {
                    int s = Math.min(Math.min(nums[i], nums[j]), -nums[i] - nums[j]);
                    Set<Integer> sc = repeated.get(s);
                    if (null == sc) {
                        sc = new HashSet<>(nums.length);
                        repeated.put(s, sc);
                    }
                    int b = Math.max(Math.max(nums[i], nums[j]), -nums[i] - nums[j]);
                    if (!sc.contains(b)) {
                        List<Integer> match = new ArrayList<>();
                        match.add(nums[i]);
                        match.add(nums[j]);
                        match.add(-nums[i] - nums[j]);
                        result.add(match);
                        sc.add(b);
                    }
                }
                checkedSecond.add(nums[j]);
            }
            checkedFirst.add(nums[i]);
        }
        return result;
    }
}