package exe91.decode.ways;

class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int pre = 1;
        int prePre = 1;
        int result = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '0') {
                if (s.charAt(i) == '0') {
                    return 0;
                }
                result = pre;
            } else {
                int numOf2 = Integer.parseInt(s.substring(i - 1, i + 1));
                if (s.charAt(i) == '0') {
                    if (numOf2 <= 26 && numOf2 > 0) {
                        result = prePre;
                    } else {
                        return 0;
                    }
                } else if (numOf2 <= 26) {
                    result = pre + prePre;
                } else {
                    result = pre;
                }
            }
            prePre = pre;
            pre = result;
        }
        return result;
    }
}