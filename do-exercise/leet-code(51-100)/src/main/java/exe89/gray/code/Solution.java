package exe89.gray.code;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int origin = 0;
        result.add(origin);
        if (n == 0) {
            return result;
        }
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            mask[i] = 1 << i;
        }

        boolean[] marked = new boolean[(int) Math.pow(2, n)];
        marked[0] = true;
        doGrayCode(result, mask, marked, 1, 0);
        return result;
    }

    private boolean doGrayCode(List<Integer> result, int[] mask, boolean[] marked, int markNum, int cur) {
        if (markNum == marked.length) {
            return true;
        }
        int candidate = 0;
        for (int i = 0; i < mask.length; i++) {
            candidate = (cur ^ mask[i]);
            if (!marked[candidate]) {
                break;
            }
        }
        if (candidate == 0) {
            return false;
        }
        result.add(candidate);
        marked[candidate] = true;
        boolean successor = doGrayCode(result, mask, marked, markNum + 1, candidate);
        if (successor) {
            return true;
        } else {
            result.remove(result.size() - 1);
            marked[candidate] = false;
            return false;
        }
    }
}