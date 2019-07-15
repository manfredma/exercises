package exe58.length.of.last.word;

class Solution {
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int index = s.lastIndexOf(" ");
        if (index < 0) {
            return s.length();
        } else {
            return s.length() - 1 - index;
        }
    }
}