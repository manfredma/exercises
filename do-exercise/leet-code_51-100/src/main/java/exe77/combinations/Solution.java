package exe77.combinations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        if (k > n) {
            return result;
        }


        int[] digits = new int[n];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = i + 1;
        }


        return doCombine(digits, k, 0, digits.length - 1);
    }

    private List<List<Integer>> doCombine(int[] digits, int k, int begin, int end) {
        List<List<Integer>> result = new ArrayList<>();
        if (end - begin + 1 < k) {
            return result;
        } else if (end - begin + 1 == k) {
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                integers.add(digits[begin + i]);
            }
            result.add(integers);
            return result;
        } else if (k == 1) {
            for (int i = 0; i < end - begin + 1; i++) {
                List<Integer> re = new ArrayList<>();
                re.add(digits[begin + i]);
                result.add(re);
            }
            return result;
        }
        List<List<Integer>> x = doCombine(digits, k - 1, begin + 1, end);
        for (List<Integer> integers : x) {
            List<Integer> integers1 = new ArrayList<>();
            integers1.add(digits[begin]);
            integers1.addAll(integers);
            result.add(integers1);
        }
        result.addAll(doCombine(digits, k, begin + 1, end));
        return result;
    }
}