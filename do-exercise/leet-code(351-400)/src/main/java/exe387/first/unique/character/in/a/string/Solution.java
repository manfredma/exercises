package exe387.first.unique.character.in.a.string;

class Solution {
    public int firstUniqChar(String s) {
        if (null == s || "".equals(s)) {
            return -1;
        }
        int[] letterFrequency = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letterFrequency[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letterFrequency[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}