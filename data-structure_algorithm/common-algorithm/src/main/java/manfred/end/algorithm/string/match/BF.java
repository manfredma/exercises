package manfred.end.algorithm.string.match;

/**
 * BF 算法中的 BF 是 Brute Force 的缩写
 * <p>
 * 暴力（朴素）匹配算法
 *
 * @author Manfred since 2019/8/21
 */
public class BF implements StringMatcher {
    @Override
    public boolean match(String src, String pattern) {
        if (src.length() < pattern.length()) {
            return false;
        }

        for (int i = 0; i < src.length() - pattern.length() + 1; i++) {
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
        return false;
    }
}
