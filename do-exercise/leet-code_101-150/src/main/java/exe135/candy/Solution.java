package exe135.candy;

import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int len = ratings.length;
        int[] c = new int[len];
        Arrays.fill(c, 1);
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < len; i++) {
                if (i > 0 && ratings[i] > ratings[i - 1]) {
                    if (c[i] <= c[i - 1]) {
                        c[i] = c[i - 1] + 1;
                        changed = true;
                    }
                }
                if (i < len - 1 && ratings[i] > ratings[i + 1]) {
                    if (c[i] <= c[i + 1]) {
                        c[i] = c[i + 1] + 1;
                        changed = true;
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += c[i];
        }
        return result;
    }
}