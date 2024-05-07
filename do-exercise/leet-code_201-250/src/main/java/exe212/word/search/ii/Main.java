/*
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell,
where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.



Example:

Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]


Note:

All inputs are consist of lowercase letters a-z.
The values of words are distinct.

 */
package exe212.word.search.ii;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findWords(new char[][]{
                        {'o', 'a', 'b', 'n'},
                        {'o', 't', 'a', 'e'},
                        {'a', 'h', 'k', 'r'},
                        {'a', 'f', 'l', 'v'},
                },
                new String[]{"oa", "oaa"}));

        System.out.println(solution.findWords(new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                },
                new String[]{"oath", "pea", "eat", "rain"}));

        System.out.println(solution.findWords(new char[][]{
                        {'a', 'b'},
                        {'c', 'd'}
                },
                new String[]{"abcb"}));

    }
}
