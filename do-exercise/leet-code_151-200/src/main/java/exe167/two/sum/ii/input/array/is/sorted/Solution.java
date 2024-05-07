package exe167.two.sum.ii.input.array.is.sorted;

import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Arrays.fill(result, -1);
        int[] re = new int[numbers.length];
        for (int k = 0; k < numbers.length; k++) {
            re[k] = target - numbers[numbers.length - 1 - k];
        }
        int i = 0, j = 0;
        while (i < numbers.length && j < numbers.length) {
            if (numbers[i] == re[j]) {
                result[0] = Math.min(i, numbers.length - 1 - j) + 1;
                result[1] = Math.max(i, numbers.length - 1 - j) + 1;
                break;
            } else if (numbers[i] > re[j]) {
                j++;
            } else {
                i++;
            }
        }
        return result;
    }
}