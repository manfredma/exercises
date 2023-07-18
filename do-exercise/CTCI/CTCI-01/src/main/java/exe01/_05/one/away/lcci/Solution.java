package exe01._05.one.away.lcci;

class Solution {
    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        int cur = m - n, count = 1;
        if (cur > 1 || cur < -1) {
            return false;
        }
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            if (first.charAt(i) != second.charAt(j)) {
                if (cur == 1) {
                    j--;
                } else if (cur == -1) {
                    i--;
                }
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return true;
    }
}