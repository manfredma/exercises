package exe205.isomorphic.strings;

import java.util.*;

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (Objects.equals(s, t)) {
            return true;
        }

        Map<Character, Character> mapS2T = new HashMap<>();
        Set<Character> mappedT = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (mapS2T.containsKey(a)) {
                if (mapS2T.get(a) != b) {
                    return false;
                }
            } else if (mappedT.contains(b)) {
                return false;
            } else {
                mapS2T.put(a, b);
                mappedT.add(b);
            }
        }
        return true;
    }
}