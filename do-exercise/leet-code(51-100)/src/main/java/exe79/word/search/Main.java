/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

 */

package exe79.word.search;

public class Main {
    public static void main(String[] args) {
        char[][] x = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        Solution solution = new Solution();
        System.out.println(solution.exist(x, "AB"));
        System.out.println(solution.exist(x, "ABCCED"));
        System.out.println(solution.exist(x, "SEE"));
        System.out.println(solution.exist(x, "ABCB"));
    }
}
