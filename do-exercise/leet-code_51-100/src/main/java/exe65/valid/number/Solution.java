package exe65.valid.number;

class Solution {

    private char charOfZero = '0';
    private char charOfOne = '1';
    private char charOfNine = '9';

    public boolean isNumber(String s) {
        if (null == s) {
            return false;
        }
        s = s.trim();
        if (s.length() == 0) {
            return false;
        }

        // 处理只有单个字符的情况
        if (s.length() == 1) {
            return (s.charAt(0) >= charOfZero && s.charAt(0) <= charOfNine);
        }

        // 处理符号位
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            s = s.substring(1);
        }

        // 按符号E拆解
        if (s.contains("e")  && s.indexOf("e") == s.lastIndexOf("e")) {
            String[] x = s.split("e");
            if (x.length == 2) {
                // 处理符号位
                if (x[1].length() > 1 && (x[1].charAt(0) == '+' || x[1].charAt(0) == '-')) {
                    x[1] = x[1].substring(1);
                }
                return isNormalDecimal(x[0]) && isAllDigital(x[1]);
            } else {
                return false;
            }
        } else {
            return isNormalDecimal(s);
        }
    }

    private boolean isNormalDecimal(String s) {
        if (null == s || 0 == s.length()) {
            return false;
        }
        // 按符号'.'拆解
        if (s.contains(".") && s.indexOf(".") == s.lastIndexOf(".")) {
            String[] x = s.split("\\.");
            if (x.length == 2) {
                if (x[0].length() == 0 && x[1].length() == 0) {
                    return false;
                }
                return (x[0].length() == 0 || isAllDigital(x[0])) && (x[1].length() == 0 || isAllDigital(x[1]));
            } else if (x.length == 1) {
                return isAllDigital(x[0]);
            } else {
                return false;
            }
        } else {
            return isAllDigital(s);
        }
    }

    private boolean isAllDigital(String s) {
        if (null == s || 0 == s.length()) {
            return false;
        }
        if (s.length() == 1) {
            return (s.charAt(0) >= charOfZero && s.charAt(0) <= charOfNine);
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < charOfZero || s.charAt(i) > charOfNine) {
                return false;
            }
        }
        return true;
    }
}