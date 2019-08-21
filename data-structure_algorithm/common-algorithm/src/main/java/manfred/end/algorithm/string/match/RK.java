package manfred.end.algorithm.string.match;

/**
 * RK 算法的全称叫 Rabin-Karp 算法，是由它的两位发明者命名的
 *
 * @author Manfred since 2019/8/21
 */
public class RK implements StringMatcher {

    int[] table = {(int) Math.pow(26, 0), (int) Math.pow(26, 1),
            (int) Math.pow(26, 2), (int) Math.pow(26, 3),
            (int) Math.pow(26, 4), (int) Math.pow(26, 5),
            (int) Math.pow(26, 6), (int) Math.pow(26, 7),
            (int) Math.pow(26, 8), (int) Math.pow(26, 9)
    };

    @Override
    public boolean match(String src, String pattern) {
        if (src.length() < pattern.length()) {
            return false;
        }
        int patternLength = pattern.length();
        int patternHash = hash(pattern);
        int[] hash = new int[src.length() - patternLength + 1];
        hash[0] = hash(src, 0, pattern.length());
        for (int i = 0; i < src.length() - patternLength + 1; i++) {
            if (i > 0) {
                hash[i] = (hash[i - 1] - table[patternLength - 1] * (src.charAt(i - 1) - 'a')) * 26 + (src.charAt(i + patternLength - 1) - 'a');
            }
            int srcHash = hash[i];
            if (srcHash == patternHash) {
                boolean equal = true;
                for (int j = 0; j < pattern.length(); j++) {
                    if (pattern.charAt(j) != src.charAt(i + j)) {
                        equal = false;
                        break;
                    }
                }
                if (equal) {
                    return true;
                }
            }
        }
        return false;
    }

    private int hash(String s) {
        return hash(s, 0, s.length());
    }

    private int hash(String s, int pos, int length) {
        int i = 0;
        for (int j = pos; j < length + pos; j++) {
            i = (s.charAt(j) - 'a') + i * 26;
        }
        return i;
    }
}
