package exe171.excel.sheet.column.number;

class Solution {
    public int titleToNumber(String s) {
        int result = 0;
        if (s == null || "".equals(s)) {
            return result;
        }

        for (int i = 0; i < s.length(); i++) {
            char z = s.charAt(s.length() - 1 - i);
            result += (int) Math.pow(26, i) * (z - 'A' + 1);
        }
        return result;
    }
}