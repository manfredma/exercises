package exe165.compare.version.numbers;

import java.util.Objects;

class Solution {
    public int compareVersion(String version1, String version2) {
        if (Objects.equals(version1, version2)) {
            return 0;
        }
        if (version1 == null) {
            return -1;
        } else if (version2 == null) {
            return 1;
        } else {
            String[] v1 = version1.split("\\.");
            String[] v2 = version2.split("\\.");
            int index = 0;
            while (index < v1.length && index < v2.length) {
                int x = compare(v1[index], v2[index]);
                if (x == 0) {
                    index++;
                } else {
                    return x;
                }
            }

            if (index < v1.length) {
                for (int i = index; i < v1.length; i++) {
                    if (!isZero(v1[i])) {
                        return 1;
                    }
                }
            }

            if (index < v2.length) {
                for (int i = index; i < v2.length; i++) {
                    if (!isZero(v2[i])) {
                        return -1;
                    }
                }
            }
            return 0;
        }
    }

    private boolean isZero(String s) {
        if (s == null) {
            return true;
        }
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    private int compare(String s, String s1) {
        if (Objects.equals(s, s1)) {
            return 0;
        }
        if (s == null) {
            return -1;
        } else if (s1 == null) {
            return 1;
        }
        s = s.trim();
        while (true) {
            if (s.charAt(0) == '0' && s.length() > 1) {
                s = s.substring(1);
            } else {
                break;
            }
        }
        s1 = s1.trim();
        while (true) {
            if (s1.charAt(0) == '0' && s1.length() > 1) {
                s1 = s1.substring(1);
            } else {
                break;
            }
        }
        if (Objects.equals(s, s1)) {
            return 0;
        }

        if (s.length() > s1.length()) {
            return 1;
        } else if (s.length() < s1.length()) {
            return -1;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) > s1.charAt(i)) {
                    return 1;
                } else if (s.charAt(i) < s1.charAt(i)) {
                    return -1;
                }
            }
        }
        return 0;
    }
}