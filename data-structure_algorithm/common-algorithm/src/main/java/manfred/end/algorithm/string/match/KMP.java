package manfred.end.algorithm.string.match;

/**
 * KMP 算法是根据三位作者（D.E.Knuth，J.H.Morris 和 V.R.Pratt）的名字来命名的
 * 算法思想：
 * -> 与BM算法类似, 主要基于好前缀的思想来做
 *
 * @author Manfred since 2019/8/21
 */
public class KMP implements StringMatcher {
    @Override
    public boolean match(String src, String pattern) {
        int[] next = getNexts(pattern);
        int j = 0;
        for (int i = 0; i < src.length(); ++i) {
            // 一直找到 a[i] 和 b[j]
            while (j > 0 && src.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1] + 1;
            }
            if (src.charAt(i) == pattern.charAt(j)) {
                ++j;
            }
            if (j == pattern.length()) {
                return true;
            }
        }
        return false;
    }


    // b 表示模式串，m 表示模式串的长度
    private int[] getNexts(String b) {
        int[] next = new int[b.length()];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < b.length(); ++i) {
            while (k != -1 && b.charAt(k + 1) != b.charAt(i)) {
                k = next[k];
            }
            if (b.charAt(k + 1) == b.charAt(i)) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

}
