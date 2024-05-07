package exe28.implement.strstr;

class Solution {
    public int strStr(String haystack, String needle) {
        if (null == needle || "".equals(needle)) {
            return 0;
        }
        if (null == haystack || "".equals(haystack)) {
            return -1;
        }

        return haystack.indexOf(needle);
    }
}