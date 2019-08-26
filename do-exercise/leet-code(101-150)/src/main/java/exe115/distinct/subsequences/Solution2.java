package exe115.distinct.subsequences;

/**
 * 动规方案
 */
class Solution2 {
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) {
            return 0;
        }
        if (s.equals(t)) {
            return 1;
        }
        if ("".equals(s)) {
            return 0;
        }

        int[][] numDistinct = new int[s.length()][t.length()];
        if (s.charAt(s.length() - 1) == t.charAt(t.length() - 1)) {
            numDistinct[s.length() - 1][t.length() - 1] = 1;
        }
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == t.charAt(t.length() - 1)) {
                numDistinct[i][t.length() - 1] = numDistinct[i + 1][t.length() - 1] + 1;
            } else {
                numDistinct[i][t.length() - 1] = numDistinct[i + 1][t.length() - 1];
            }

        }


        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = t.length() - 2; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    numDistinct[i][j] = numDistinct[i + 1][j + 1] + numDistinct[i + 1][j];
                } else {
                    numDistinct[i][j] = numDistinct[i + 1][j];
                }
            }
        }
        return numDistinct[0][0];
    }
}