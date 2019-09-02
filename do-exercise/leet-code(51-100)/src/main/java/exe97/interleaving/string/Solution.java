package exe97.interleaving.string;

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if ("".equals(s1)) {
            return s2.equals(s3);
        } else if ("".equals(s2)) {
            return s1.equals(s3);
        } else {
            if (s1.charAt(0) == s3.charAt(0)) {
                boolean subIsInterleave1 = isInterleave(s1.substring(1), s2, s3.substring(1));
                if (subIsInterleave1) {
                    return true;
                }
            }
            if (s2.charAt(0) == s3.charAt(0)) {
                return isInterleave(s1, s2.substring(1), s3.substring(1));
            } else {
                return false;
            }
        }
    }
}