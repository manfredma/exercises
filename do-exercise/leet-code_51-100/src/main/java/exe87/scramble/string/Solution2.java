package exe87.scramble.string;

/**
 * 动态规划版本
 */
class Solution2 {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if (s1.length() == 1 || s2.length() == 1) {
            return false;
        }

        int length = s1.length();
        boolean[][][] cache = new boolean[length][length][length + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    cache[i][j][1] = true;
                }
            }
        }
        for (int k = 2; k < length + 1; k++) {
            for (int i = 0; i < length - k + 1; i++) {
                for (int j = 0; j < length - k + 1; j++) {
                    for (int l = 1; l < k; l++) {
                        if (cache[i][j][l] && cache[i + l][j + l][k - l]) {
                            cache[i][j][k] = true;
                            break;
                        } else if (cache[i][j + k - l][l] && cache[i + l][j][k - l]) {
                            cache[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return cache[0][0][length];
    }
}