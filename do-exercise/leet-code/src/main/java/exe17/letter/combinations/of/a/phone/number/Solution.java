package exe17.letter.combinations.of.a.phone.number;

import java.util.*;

class Solution {
    private Map<Character, List<Character>> mapping = new HashMap<>();

    {
        mapping.put('1', new ArrayList<>());
        mapping.put('2', Arrays.asList('a', 'b', 'c'));
        mapping.put('3', Arrays.asList('d', 'e', 'f'));
        mapping.put('4', Arrays.asList('g', 'h', 'i'));
        mapping.put('5', Arrays.asList('j', 'k', 'l'));
        mapping.put('6', Arrays.asList('m', 'n', 'o'));
        mapping.put('7', Arrays.asList('p', 'q', 'r', 's'));
        mapping.put('8', Arrays.asList('t', 'u', 'v'));
        mapping.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        mapping.put('0', new ArrayList<>());
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return result;
        }
        if (digits.length() == 1) {
            List<Character> r = mapping.get(digits.charAt(0));
            for (Character character : r) {
                result.add(String.valueOf(character));
            }
            return result;
        }

        List<String> other = letterCombinations(digits.substring(1));
        List<Character> r = mapping.get(digits.charAt(0));
        if (r.isEmpty()) {
            return other;
        }
        for (Character character : r) {
            for (String s : other) {
                result.add(character + s);
            }
        }
        return result;
    }
}