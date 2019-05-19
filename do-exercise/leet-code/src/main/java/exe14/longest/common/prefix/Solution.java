package exe14.longest.common.prefix;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (null == strs || 0 == strs.length ) {
            return result;
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (null == strs[i]) {
                minLen = 0;
            } else if (minLen > strs[i].length()) {
                minLen = strs[i].length();
            }
        }

        for (int i = 0; i < minLen; i++) {
            char x = strs[0].charAt(i);
            for (int i1 = 1; i1 < strs.length; i1++) {
                if (strs[i1].charAt(i) != x) {
                    return result;
                }
            }
            result += x;
        }
        return result;
        
    }
}