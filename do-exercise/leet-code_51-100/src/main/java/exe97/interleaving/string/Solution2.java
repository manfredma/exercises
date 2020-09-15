package exe97.interleaving.string;

import java.util.Arrays;

/*
动态规划版本
 */
class Solution2 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if ("".equals(s1)) {
            return s2.equals(s3);
        } else if ("".equals(s2)) {
            return s1.equals(s3);
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] x = new boolean[s1.length() + 1][s2.length() + 1];
        x[s1.length()][s2.length()] = true;
        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) == s3.charAt(s2.length() + i)) {
                x[i][s2.length()] = true;
            } else {
                // 只要有一个不满足的，则比起长的子串都不满足
                break;
            }
        }
        for (int i = s2.length() - 1; i >= 0; i--) {
            if (s2.charAt(i) == s3.charAt(s1.length() + i)) {
                x[s1.length()][i] = true;
            } else {
                break;
            }
        }
//        print(s1, s2, s3, x);
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                boolean t1 = x[i + 1][j] && s1.charAt(i) == s3.charAt(i + j);
                boolean t2 = x[i][j + 1] && s2.charAt(j) == s3.charAt(i + j);
                x[i][j] = t1 || t2;
            }

//            print(s1, s2, s3, x);
        }
        return x[0][0];
    }

    private void print(String s1, String s2, String s3, boolean[][] x) {
        System.out.println("--------------------------------------");
        System.out.println(s1 + "---" + s2 + "---" + s3);
        for (int i = 0; i < x.length; i++) {
            System.out.println(Arrays.toString(x[i]));
        }
    }
}