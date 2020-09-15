package exe66.plus.one;

import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = 0; i < digits.length; i++) {
            digits[digits.length - 1 - i] = digits[digits.length - 1 - i] + 1;
            if (digits[digits.length - 1 - i] / 10 == 0) {
                break;
            }
            digits[digits.length - 1 - i] = digits[digits.length - 1 - i] % 10;
        }
        if (digits[0] == 0) {
            int[] r = new int[digits.length + 1];
            r[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                r[i + 1] = digits[i];
            }
            return r;
        }
        return digits;
    }
}