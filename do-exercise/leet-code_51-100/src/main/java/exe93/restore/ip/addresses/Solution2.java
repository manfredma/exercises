package exe93.restore.ip.addresses;

import java.util.ArrayList;
import java.util.List;

/*
动态规划版本
 */
class Solution2 {
    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4) {
            return new ArrayList<>();
        }
        List<String>[][] cache = new List[4][s.length()];
        for (int i = 0; i < 4; i++) {
            for (int j = s.length() - 1; j >= 0; j--) {
                cache[i][j] = new ArrayList<>();
                if (i > 0) {
                    if (j == s.length() - 1) {
                        continue;
                    }
                    if (s.charAt(j) == '0') {
                        for (int k = 0; k < cache[i - 1][j + 1].size(); k++) {
                            cache[i][j].add("0." + cache[i - 1][j + 1].get(k));
                        }
                    } else {
                        for (int k = j + 1; k < s.length(); k++) {
                            if (s.substring(j, k).length() < 4 && Integer.parseInt(s.substring(j, k)) <= 255) {
                                for (int l = 0; l < cache[i - 1][k].size(); l++) {
                                    cache[i][j].add(s.substring(j, k) + "." + cache[i - 1][k].get(l));
                                }
                            }
                        }
                    }
                } else {
                    if (s.charAt(j) == '0') {
                        if (j == s.length() - 1) {
                            cache[i][j].add(s.substring(j));
                        }
                    } else {
                        if (s.substring(j).length() < 4 && Integer.parseInt(s.substring(j)) <= 255) {
                            cache[i][j].add(s.substring(j));
                        }
                    }
                }
            }
        }
        return cache[3][0];
    }
}