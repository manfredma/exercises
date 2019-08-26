package exe8.string.to.integer.atoi;

class Solution {


    public int myAtoi(String str) {
        long result = 0;
        int sign = 1;
        if (null == str || "".equals(str)) {
            return (int) result;
        }

        char[] chars = str.toCharArray();

        // 跳过开始的空格
        int index = 0;
        for (char aChar : chars) {
            if (' ' == aChar) {
                index++;
            } else {
                break;
            }
        }

        if (index >= chars.length) {
            return (int)result;
        }

        // 判断首位是否合法，以及是否符号
        if ('-' == chars[index]) {
            sign = -1;
            index++;
        } else if ('+' == chars[index]) {
            index++;
        }

        for (int i = index; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                result = result * 10 + Character.digit(chars[i], 10);
                if (sign == -1 && result > Math.abs((long) Integer.MIN_VALUE)) {
                    // 溢出
                    return Integer.MIN_VALUE;
                } else if (sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                break;
            }
        }
        return (int) result * sign;
    }
}