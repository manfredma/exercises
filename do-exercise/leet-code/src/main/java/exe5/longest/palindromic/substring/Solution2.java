package exe5.longest.palindromic.substring;


import java.util.Arrays;

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
            for (int j = 1; j <= i + 1; j++) {
                if (j == i + 1 || i + j > filledChars.length - 1 || filledChars[i - j] != filledChars[i + j]) {
                    len[i] = j - 1;
                    if (len[i] > max) {
                        pos = i;
                        max = len[i];
                    }
                    break;
                }
            }
        }

        char[] hit = Arrays.copyOfRange(filledChars, pos - max, pos + max + 1);
        char[] originHit = new char[max];
        for (int i = 0; i < hit.length; i++) {
            if (i % 2 == 1) {
                originHit[i / 2] = hit[i];
            }
        }
        return new String(originHit);
    }
}