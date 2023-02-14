package exe212.word.search.ii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，返回所有二维网格上的单词。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * 示例 1：
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words =
 * ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/word-search-ii">leetcode 链接</a>
 */
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // 构造 trie 树
        TrieNode trieNode = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            trieNode.insert(words[i]);
        }
        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                StringBuilder sb = new StringBuilder();
                dfs(result, board, i, j, sb, trieNode);
            }
        }
        return new ArrayList<>(result);
    }

    private void dfs(
            Set<String> result, char[][] board, int i, int j, StringBuilder sb,
            TrieNode trieNode) {
        char cur = board[i][j];
        int index = cur - 'a';
        if (board[i][j] == '#' || trieNode.children[index] == null) {
            // 如果没有元素可以匹配
            return;
        }
        sb.append(cur);
        if (trieNode.children[index].isWord) {
            result.add(sb.toString());
        }
        board[i][j] = '#';

        if (i > 0) {
            dfs(result, board, i - 1, j, sb, trieNode.children[index]);
        }
        if (i < board.length - 1) {
            dfs(result, board, i + 1, j, sb, trieNode.children[index]);
        }
        if (j > 0) {
            dfs(result, board, i, j - 1, sb, trieNode.children[index]);
        }
        if (j < board[i].length - 1) {
            dfs(result, board, i, j + 1, sb, trieNode.children[index]);
        }
        // 还原数据，以便于探索其他路径
        sb.delete(sb.length() - 1, sb.length());
        board[i][j] = cur;
    }

    static class TrieNode {
        boolean isWord = false;

        TrieNode[] children = new TrieNode[26];

        void insert(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.toCharArray().length; i++) {
                int index = word.charAt(i) - 'a';
                if (trieNode.children[index] == null) {
                    trieNode.children[index] = new TrieNode();
                }
                if (i == word.length() - 1) {
                    trieNode.children[index].isWord = true;
                }
                trieNode = trieNode.children[index];
            }
        }
    }
}