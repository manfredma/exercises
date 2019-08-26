package exe13.roman.to.integer;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Integer> symbols = new HashMap<>();
    {
        symbols.put('I', 1);
        symbols.put('V', 5);
        symbols.put('X', 10);
        symbols.put('L', 50);
        symbols.put('C', 100);
        symbols.put('D', 500);
        symbols.put('M', 1000);
    }
    public int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() -1 || symbols.get(s.charAt(i)) >= symbols.get(s.charAt(i + 1)) ) {
                result += symbols.get(s.charAt(i));
            } else {
                result -= symbols.get(s.charAt(i));
            }
        }
        return result;
    }
}