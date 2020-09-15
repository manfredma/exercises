package exe132.palindrome.partitioning.ii;

class Solution {
    public int minCut(String s) {
        boolean[][] flag = new boolean[s.length()][s.length()];
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            flag[i][i] = true;
            if (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                flag[i][i + 1] = true;
            }
        }
        for (int l = 3; l <= len; ++l) {
            for (int i = 0; i + l - 1 < len; ++i) {
                if (flag[i + 1][i + l - 2] && s.charAt(i) == s.charAt(i + l - 1)) {
                    flag[i][i + l - 1] = true;
                }
            }
        }
        int dp[] = new int[len];
        for (int i = 0; i < len; ++i) {
            dp[i] = i;
            if (flag[0][i]) {
                dp[i] = 0;
            } else {
                dp[i] = i;
                for (int j = 1; j <= i; ++j) {
                    if (flag[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[len - 1];
    }
}