package exe166.fraction.to.recurring.decimal;

import java.util.HashMap;

class Solution {
    public String fractionToDecimal(int numerator1, int denominator1) {
        long numerator = numerator1;
        long denominator = denominator1;
        // x.y 形式
        String sigh = "";
        if (numerator * denominator < 0) {
            sigh = "-";
        }
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        long x = numerator / denominator;
        long remainder = numerator % denominator;
        if (remainder == 0) {
            return sigh + String.valueOf(x);
        }
        String y = "";
        // 计算小数部分
        HashMap<Long, Integer> r = new HashMap<>();
        int index = 0;
        while (remainder != 0 && !r.containsKey(remainder)) {
            r.put(remainder, index++);
            remainder = remainder * 10;
            y = y + remainder / denominator;
            remainder = remainder % denominator;
        }

        if (remainder == 0) {
            return sigh + x + "." + y;
        } else {
            int i = r.get(remainder);
            if (i == 0) {
                y = x + ".(" + y + ")";
            } else {
                y = x + "." + y.substring(0, i) + "(" + y.substring(i) + ")";
            }
        }
        return sigh + y;
    }
}