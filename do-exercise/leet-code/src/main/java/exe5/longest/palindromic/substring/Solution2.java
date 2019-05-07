package exe5.longest.palindromic.substring;


class Solution2 {
    public String longestPalindrome(String s) {
        if (null == s || "".equals(s)) {
            return s;
        }
        // 填充，变成奇数个字符，并且不影响回文结果
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            stringBuilder.append('#').append(chars[i]);
        }
        stringBuilder.append('#');
        char[] filledChars = stringBuilder.toString().toCharArray();

        // 开始计算
        int[] len = new int[filledChars.length];
        len[1] = 1;
        int max = 1;
        int pos = 1;
        for (int i = 2; i < filledChars.length; i++) {
            for (int j = 1; j < i + 1; j++) {
                if (i + j > filledChars.length - 1 || filledChars[i - j] != filledChars[i + j]) {
                    len[i] = j;
                    if (len[i] > max) {
                        pos = i;
                        max = len[i];
                    }
                }
            }
        }

        // 构造返回值
        if ((pos & 1) == 1) {
            // 当前位置非填充值
            char[] result = new char[max];
            for (int i = 0; i < max; i++) {
                result[i] = filledChars[i];
            }
            return new String(result);
        } else {

        }
        return null;
    }
}