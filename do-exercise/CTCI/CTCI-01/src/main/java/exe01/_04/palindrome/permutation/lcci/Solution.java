package exe01._04.palindrome.permutation.lcci;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> index = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (index.containsKey(c)) {
                index.put(c, index.get(c) + 1);
            } else {
                index.put(c, 1);
            }
        }
        int oddNum = 0;

        for (Integer count : index.values()) {
            if (count % 2 != 0) {
                oddNum++;
            }
        }

        if (s.length() % 2 == 1) {
            return oddNum == 1;
        } else {
            return oddNum == 0;
        }
    }
}