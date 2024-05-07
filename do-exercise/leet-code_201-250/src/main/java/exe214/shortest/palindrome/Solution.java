package exe214.shortest.palindrome;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aacecaaa"
 * 输出："aaacecaaa"
 * 示例 2：
 * <p>
 * 输入：s = "abcd"
 * 输出："dcbabcd"
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/shortest-palindrome">...</a>
 */
class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        // 使用马拉车算法计算各个最长回文串

        // 初始化辅助存储结构
        char[] originalChars = s.toCharArray();
        int len = 2 * originalChars.length + 1;

        // 增加#号
        char[] m = new char[len];
        for (int i = 0; i < s.length(); i++) {
            m[2 * i] = '#';
            m[2 * i + 1] = originalChars[i];
        }
        m[len - 1] = '#';
        int[] p = new int[len];

        int rightmost = 0;
        int rightmostCenter = 0;

        // 计算p(因为只需要前缀的最长回文串，超过一半的回文串是不可能包含前缀的)
        for (int i = 1; i < len / 2 + 1; i++) {
            if (rightmost <= i) {
                // 以 i 为中心扩展判断
                int dis = centerExtend(m, i, 0);
                p[i] = dis;
                if (i + dis > rightmost) {
                    rightmostCenter = i;
                    rightmost = i + dis;
                }
            } else {
                int sym = 2 * rightmostCenter - i;
                int symLen = p[sym];
                if (rightmost > i + symLen) {
                    p[i] = p[sym];
                } else {
                    int dis = centerExtend(m, i, rightmost - i);
                    p[i] = dis;
                    if (i + dis > rightmost) {
                        rightmostCenter = i;
                        rightmost = i + dis;
                    }
                }
            }
        }

        // 查询开头的最长回文串
        int max = 1;
        for (int i = 0; i < len; i++) {
            if (i - p[i] == 0) {
                if (p[i] > max) {
                    max = p[i];
                }
            }
        }

        StringBuilder res = new StringBuilder(s);
        for (int i = max; i < s.length(); i++) {
            res.insert(0, s.charAt(i));
        }

        return res.toString();
    }

    private static int centerExtend(char[] m, int i, int dis) {
        int result = dis;
        int len = m.length;
        while (dis + i < len && i - dis >= 0) {
            if (m[i + dis] == m[i - dis]) {
                result = dis;
                dis = dis + 1;
            } else {
                break;
            }
        }
        return result;
    }
}