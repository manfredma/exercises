package exe5.longest.palindromic.substring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String longestPalindrome(String s) {
        if (null == s || "".equals(s)) {
            return s;
        }
        char[] chars = s.toCharArray();
        String result = String.valueOf(chars, 0, 1);
        Map<Character, List<Integer>> charsIndices = new HashMap<Character, List<Integer>>(s.length());
        for (int i = 0; i < chars.length; i++) {
            if (!charsIndices.containsKey(chars[i])) {
                charsIndices.put(chars[i], new ArrayList<Integer>(s.length()));
            }
            List<Integer> indices = charsIndices.get(chars[i]);
            for (Integer index : indices) {
                if (i - index + 1 < result.length()) {
                    break;
                }
                boolean missMatch = false;
                for (int j = 0; j < (i - index + 1) / 2; j++) {
                    if (chars[j + index] != chars[i - j]) {
                        missMatch = true;
                        break;
                    }
                }
                if (!missMatch) {
                    result = String.valueOf(chars, index, (i - index + 1));
                    break;
                }
            }

            charsIndices.get(chars[i]).add(i);
        }
        return result;
    }
}