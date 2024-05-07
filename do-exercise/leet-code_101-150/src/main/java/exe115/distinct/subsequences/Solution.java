package exe115.distinct.subsequences;

/**
 * 递归方案
 */
class Solution {
    public int numDistinct(String s, String t) {
        if ("".equals(t)) {
            return 1;
        }
        if (s.length() < t.length()) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                result += numDistinct(s.substring(i + 1), t.substring(1));
            }
        }
        return result;
    }
}