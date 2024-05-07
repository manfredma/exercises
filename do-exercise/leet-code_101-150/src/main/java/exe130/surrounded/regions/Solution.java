package exe130.surrounded.regions;

import java.util.Stack;

class Solution {
    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        if (board.length < 3 || board[0].length < 3) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                Stack<Coordinate> stack = createStack(board, i, 0);
                doResolve(board, stack);
            }
            if (board[i][board[0].length - 1] == 'O') {
                Stack<Coordinate> stack = createStack(board, i, board[0].length - 1);
                doResolve(board, stack);
            }
        }
        for (int i = 1; i < board[0].length - 1; i++) {
            if (board[0][i] == 'O') {
                Stack<Coordinate> stack = createStack(board, 0, i);
                doResolve(board, stack);
            }
            if (board[board.length - 1][i] == 'O') {
                Stack<Coordinate> stack = createStack(board, board.length - 1, i);
                doResolve(board, stack);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'z') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private Stack<Coordinate> createStack(char[][] board, int x, int y) {
        Stack<Coordinate> stack = new Stack<>();
        board[x][y] = 'z';
        stack.push(new Coordinate(x, y));
        return stack;
    }

    private void doResolve(char[][] board, Stack<Coordinate> stack) {
        while (!stack.isEmpty()) {
            Coordinate coordinate = stack.pop();
            if (needSolve(board, coordinate.x - 1, coordinate.y)) {
                board[coordinate.x - 1][coordinate.y] = 'z';
                stack.push(new Coordinate(coordinate.x - 1, coordinate.y));
            }
            if (needSolve(board, coordinate.x + 1, coordinate.y)) {
                board[coordinate.x + 1][coordinate.y] = 'z';
                stack.push(new Coordinate(coordinate.x + 1, coordinate.y));
            }
            if (needSolve(board, coordinate.x, coordinate.y - 1)) {
                board[coordinate.x][coordinate.y - 1] = 'z';
                stack.push(new Coordinate(coordinate.x, coordinate.y - 1));
            }
            if (needSolve(board, coordinate.x, coordinate.y + 1)) {
                board[coordinate.x][coordinate.y + 1] = 'z';
                stack.push(new Coordinate(coordinate.x, coordinate.y + 1));
            }
        }
    }

    private boolean needSolve(char[][] board, int x, int y) {
        if (x > board.length - 1 || x < 0) {
            return false;
        } else if (y > board[0].length - 1 || y < 0) {
            return false;
        }
        return board[x][y] == 'O';
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}