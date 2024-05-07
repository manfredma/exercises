package exe10.regular.expression.matching;

/*
 动态规划版本
 */
class Solution2 {
    private static final char WILD_CARD_ASTERISK = '*';
    private static final char WILD_CARD_DOT = '.';

    public boolean isMatch(String s, String p) {
        if (null == s) {
            return false;
        }
        if ("".equals(s) && (p == null || "".equals(p))) {
            return true;
        }
        if (p == null || "".equals(p)) {
            return false;
        }

        boolean[][] result = new boolean[s.length() + 1][p.length() + 1];
        result[0][0] = true;
        // 初始化第一行
        for (int i = 1; i < p.length() + 1; i++) {
            int charIndex = i - 1;
            if (p.charAt(charIndex) == WILD_CARD_ASTERISK) {
                // 是否需要判断越界呢？
                result[0][i] = result[0][i - 1] || result[0][i - 2];
            }
        }
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                int pCharIndex = j - 1;
                int sCharIndex = i - 1;
                if (p.charAt(pCharIndex) == WILD_CARD_ASTERISK) {
                    result[i][j] = result[i][j] || result[i][j - 1] || result[i][j - 2];
                    if (p.charAt(pCharIndex - 1) == WILD_CARD_DOT) {
                        for (int k = i + 1; k < s.length() + 1; k++) {
                            result[k][j] = result[i][j];
                        }
                    } else {
                        for (int k = i + 1; k < s.length() + 1; k++) {
                            if (s.charAt(k - 1) == p.charAt(pCharIndex - 1)) {
                                result[k][j] = result[i][j];
                            } else {
                                break;
                            }
                        }
                    }
                } else if (p.charAt(pCharIndex) == WILD_CARD_DOT) {
                    result[i][j] = result[i][j] || result[i - 1][j - 1];
                } else {
                    if (p.charAt(pCharIndex) == s.charAt(sCharIndex)) {
                        result[i][j] = result[i][j] || result[i - 1][j - 1];
                    }
                }
            }
        }
        return result[s.length()][p.length()];
    }
}