package exe43.multiply.strings;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || null == num1 || null == num2 || "0".equals(num2)) {
            return "0";
        }

        char[] num1Chars = num1.toCharArray();
        char[] num2Chars = num2.toCharArray();

        // 按位乘
        List<List<Character>> mresult = new ArrayList<>();
        for (int i = num1Chars.length - 1; i >= 0; i--) {
            List<Character> r = new ArrayList<>();
            if (num1Chars[i] == '0') {
                continue;
            }

            for (int j = 0; j < (num1Chars.length - 1 - i); j++) {
                r.add('0');
            }

            int carry = 0;
            for (int j = num2Chars.length - 1; j >= 0; j--) {
                int c = (num1Chars[i] - '0') * (num2Chars[j] - '0') + carry;
                carry = c / 10;
                c = c % 10;
                r.add(0, (char) (c + '0'));
            }
            if (carry != 0) {
                r.add(0, (char) (carry + '0'));
            }
            mresult.add(r);
        }

        // 加法
        List<Character> result = new ArrayList<>();
        for (List<Character> characters : mresult) {
            if (result.isEmpty()) {
                result = characters;
                continue;
            }
            List<Character> resultTmp = new ArrayList<>();
            int carry = 0;
            for (int i = 0; i < Math.max(result.size(), characters.size()); i++) {
                int rI = result.size() - 1 - i;
                int cI = characters.size() - 1 - i;
                int rV = 0;
                if (rI >= 0) {
                    rV = result.get(rI) - '0';
                }
                int cV = 0;
                if (cI >= 0) {
                    cV = characters.get(cI) - '0';
                }
                resultTmp.add(0, (char) (((rV + cV + carry) % 10) + '0'));
                carry = (rV + cV + carry) / 10;
            }
            if (carry > 0) {
                resultTmp.add(0, (char) (carry + '0'));
            }
            result = resultTmp;
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : result) {
            sb.append(character);
        }
        return sb.toString();
    }
}