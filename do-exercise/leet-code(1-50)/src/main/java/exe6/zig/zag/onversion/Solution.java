package exe6.zig.zag.onversion;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        if (null == s || s.length() == 0) {
            return s;
        }

        int step = numRows * 2 - 2;
        int column = numRows - 1;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i = i + step) {
            for (int j = 0; j < column; j++) {
                for (int k = 0; k < numRows; k++) {
                    if (j == 0) {
                        if (i + k < s.length()) {
                            rows[k].append(s.charAt(i + k));
                        }
                    } else if (j + k == numRows - 1) {
                        if (i + numRows + j - 1 < s.length()) {
                            rows[k].append(s.charAt(i + numRows + j - 1));
                        }
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            result.append(rows[i]);
        }
        return result.toString();
    }
}