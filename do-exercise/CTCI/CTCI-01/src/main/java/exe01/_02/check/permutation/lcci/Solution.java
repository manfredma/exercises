package exe01._02.check.permutation.lcci;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        //
        if (s1.length() != s2.length()) {
            return false;
        }

        //
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s1.toCharArray()) {
            if (charCount.containsKey(c)) {
                charCount.put(c, charCount.get(c) + 1);
            } else {
                charCount.put(c, 1);
            }
        }

        for (char c : s2.toCharArray()) {
            if (charCount.containsKey(c)) {
                if (charCount.get(c) == 0) {
                    return false;
                }
                charCount.put(c, charCount.get(c) -1);
            } else {
                return false;
            }
        }
        return true;

    }
}