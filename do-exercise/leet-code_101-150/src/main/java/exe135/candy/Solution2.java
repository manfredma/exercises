package exe135.candy;

class Solution2 {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int len = ratings.length;
        int[] increase = new int[len];
        int[] decrease = new int[len];

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                increase[i] = increase[i - 1] + 1;
            } else {
                increase[i] = 0;
            }

            if (ratings[len - 1 - i] > ratings[len - i]) {
                decrease[len - 1 - i] = decrease[len - i] + 1;
            } else {
                decrease[len - 1 - i] = 0;
            }
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += Math.max(increase[i], decrease[i]) + 1;
        }
        return result;
    }
}