package exe40.combination.sum2;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return new LinkedList<>();
        }
        Arrays.sort(candidates);
        List<List<Integer>> r = doCombinationSum(candidates, target, 0);
        List<List<Integer>> rr = new ArrayList<>(r.size());
        Set<String> chosen = new HashSet<>();
        for (List<Integer> integers : r) {
            if (chosen.add(integers.toString())) {
                rr.add(integers);
            }
        }
        return rr;

    }

    private List<List<Integer>> doCombinationSum(int[] candidates, int target, int index) {
        if (index > candidates.length - 1) {
            return new ArrayList<>();
        }
        int min = candidates[index];
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
                    List<List<Integer>> subResult = doCombinationSum(candidates, t, i + 1);
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