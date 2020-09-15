package exe79.word.search;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean exist(char[][] board, String word) {
        if (null == word || word.length() == 0) {
            return true;
        }
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }

        boolean[][] used = new boolean[board.length][board[0].length];
        List<Node> heads = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(0) == board[i][j]) {
                    heads.add(new Node(i, j));
                }
            }
        }

        for (int i = 0; i < heads.size(); i++) {
            boolean find = doSearch(board, word, used, 0, new Node(heads.get(i).x, heads.get(i).y));
            if (find) {
                return true;
            }

        }
        return false;
    }

    private boolean doSearch(char[][] board, String word, boolean[][] used, int i, Node current) {
        if (board[current.x][current.y] != word.charAt(i) || used[current.x][current.y]) {
            return false;
        }

        used[current.x][current.y] = true;
        if (i == word.length() - 1) {
            return true;
        }
        // 查左侧
        if (current.y != 0) {
            boolean find = doSearch(board, word, used, i + 1, new Node(current.x, current.y - 1));
            if (find) {
                return true;
            }
        }
        // 查右侧
        if (current.y != board[0].length - 1) {
            boolean find = doSearch(board, word, used, i + 1, new Node(current.x, current.y + 1));
            if (find) {
                return true;
            }
        }

        // 查上侧
        if (current.x != 0) {
            boolean find = doSearch(board, word, used, i + 1, new Node(current.x - 1, current.y));
            if (find) {
                return true;
            }
        }

        // 查下方
        if (current.x != board.length - 1) {
            boolean find = doSearch(board, word, used, i + 1, new Node(current.x + 1, current.y));
            if (find) {
                return true;
            }
        }

        used[current.x][current.y] = false;
        return false;
    }

    private static class Node {
        private int x;
        private int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}