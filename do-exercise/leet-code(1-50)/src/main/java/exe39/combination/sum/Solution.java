package exe39.combination.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new LinkedList<>();
        }
        Arrays.sort(candidates);
        return doCombinationSum(candidates, target, 0);
    }

    private List<List<Integer>> doCombinationSum(int[] candidates, int target, int index) {
        int min = candidates[0];
        if (target < min) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new LinkedList<>();
        for (int i = index; i < candidates.length; i++) {
            if (target == candidates[i]) {
                List<Integer> r = new ArrayList<>();
                r.add(candidates[i]);
                result.add(r);
            } else {
                int t = target - candidates[i];
                if (t >= min) {
                    List<List<Integer>> subResult = doCombinationSum(candidates, t, i);
                    for (List<Integer> integers : subResult) {
                        integers.add(0, candidates[i]);
                        result.add(integers);
                    }
                }
            }
        }
        return result;
    }
}