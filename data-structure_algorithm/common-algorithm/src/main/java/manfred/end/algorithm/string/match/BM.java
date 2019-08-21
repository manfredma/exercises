package manfred.end.algorithm.string.match;

import java.util.Arrays;

/**
 * BM（Boyer-Moore）算法
 * <p>
 * 算法思想：
 * -> 跳过一些没有必要的检测
 * PS: 当前处于未完成状态。。。
 *
 * @author Manfred since 2019/8/21
 */
public class BM implements StringMatcher {
    @Override
    public boolean match(String src, String pattern) {
        if (src.length() < pattern.length()) {
            return false;
        }

        int[] indexInPattern = resolveCharIndex(pattern);
        int[] goodSuffix = resolveGoodSuffix(pattern);
        int patternLength = pattern.length();
        int srcLength = src.length();
        int step = 0;
        for (int i = 0; i < srcLength - patternLength + 1; ) {
            boolean isMatch = true;
            for (int j = patternLength - 1; j >= 0; j--) {
                if (pattern.charAt(j) != src.charAt(i + j)) {
                    isMatch = false;
                    int matchIndex = indexInPattern[src.charAt(i + j)];
                    step = Math.max(j - matchIndex, goodSuffix[j]);
                    break;
                }
            }
            if (isMatch) {
                return true;
            }
            i = i + step;
        }
        return false;
    }

    private int[] resolveGoodSuffix(String pattern) {
        int[] result = new int[pattern.length()];
        // 如果第一个就不匹配，则默认就往后移动1
        result[pattern.length() - 1] = 1;
        for (int i = pattern.length() - 2; i >= 0; i--) {

        }
        return result;
    }

    private int[] resolveCharIndex(String s) {
        int[] result = new int[26];
        Arrays.fill(result, -1);
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a'] = i;
        }
        return result;
    }
}
