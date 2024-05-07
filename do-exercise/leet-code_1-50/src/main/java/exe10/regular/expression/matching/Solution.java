package exe10.regular.expression.matching;

class Solution {
    private static final char WILD_CARD_ASTERISK = '*';
    private static final char WILD_CARD_DOT = '.';

    public boolean isMatch(String s, String p) {
        if (null == s) {
            return false;
        }
        if ("".equals(s) && (p == null || "".equals(p))) {
            return true;
        }
        if (p == null || "".equals(p)) {
            return false;
        }

        String prePattern = obtainMatchString(p);

        if (prePattern.length() > 1) {
            // 带有通配符 *
            if (WILD_CARD_DOT == prePattern.charAt(0)) {
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(prePattern.length()))) {
                        return true;
                    }
                }
                return false;
            } else {
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(prePattern.length()))) {
                        return true;
                    }
                    if (i == s.length() || s.charAt(i) != prePattern.charAt(0)) {
                        return false;
                    }
                }
            }
        } else {
            // 不带有通配符 *
            if (WILD_CARD_DOT == prePattern.charAt(0)) {
                if (s.length() == 0) {
                    return false;
                } else {
                    return isMatch(s.substring(1), p.substring(1));
                }

            } else {
                if (s.length() == 0) {
                    return  false;
                } else if (s.charAt(0) == prePattern.charAt(0)) {
                    return isMatch(s.substring(1), p.substring(1));
                } else {
                    return false;
                }
            }

        }


        return false;
    }

    private String obtainMatchString(String p) {
        String result = p.substring(0, 1);
        for (int i = 1; i < p.length(); i++) {
            if (WILD_CARD_ASTERISK == p.charAt(i)) {
                result += p.charAt(i);
            } else {
                break;
            }
        }
        return result;
    }
}