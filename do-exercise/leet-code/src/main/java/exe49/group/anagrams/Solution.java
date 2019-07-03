package exe49.group.anagrams;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> stringIndex = new HashMap<>();

        int index = 0;

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (stringIndex.containsKey(sorted)) {
                result.get(stringIndex.get(sorted)).add(str);
            } else {
                stringIndex.put(sorted, index);
                result.add(new ArrayList<>());
                result.get(stringIndex.get(sorted)).add(str);
                index++;
            }
        }
        return result;
    }
}