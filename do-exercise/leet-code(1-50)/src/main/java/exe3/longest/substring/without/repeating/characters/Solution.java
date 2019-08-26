package exe3.longest.substring.without.repeating.characters;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        int max = 1;
        Map<Character, Integer> checked = new HashMap<Character, Integer>(s.length());
        char[] chars = s.toCharArray();
        int tmpMaxLength = 0;
        int restartIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (checked.containsKey(chars[i]) && checked.get(chars[i]) >= restartIndex) {
                // 碰到重复字符，需要重新统计
                // 1 将最大大小记录下来
                if (tmpMaxLength > max) {
                    max = tmpMaxLength;
                }
                restartIndex = checked.get(chars[i]);
                tmpMaxLength = i - restartIndex;
            } else {
                tmpMaxLength++;
            }
            checked.put(chars[i], i);
        }
        if (tmpMaxLength > max) {
            max = tmpMaxLength;
        }
        return max;
    }
}