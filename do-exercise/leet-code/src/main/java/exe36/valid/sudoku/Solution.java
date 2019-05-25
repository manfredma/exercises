package exe36.valid.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character> charactersRow = new HashSet<>();
        Set<Character> charactersColumn = new HashSet<>();
        Set<Character> characters31 = new HashSet<>();
        Set<Character> characters32 = new HashSet<>();
        Set<Character> characters33 = new HashSet<>();
        Set<Character> characters34 = new HashSet<>();
        Set<Character> characters35 = new HashSet<>();
        Set<Character> characters36 = new HashSet<>();
        Set<Character> characters37 = new HashSet<>();
        Set<Character> characters38 = new HashSet<>();
        Set<Character> characters39 = new HashSet<>();
        Map<Integer, Set<Character>> character33 = new HashMap<>();
        character33.put(0, characters31);
        character33.put(1, characters32);
        character33.put(2, characters33);
        character33.put(3, characters34);
        character33.put(4, characters35);
        character33.put(5, characters36);
        character33.put(6, characters37);
        character33.put(7, characters38);
        character33.put(8, characters39);


        // 每一行只能 1-9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!charactersRow.add(board[i][j])) {
                        return false;
                    }
                    int index = i / 3 * 3 + j / 3;
                    if (!character33.get(index).add(board[i][j])) {
                        return false;
                    }
                }
                if (board[j][i] != '.') {
                    if (!charactersColumn.add(board[j][i])) {
                        return false;
                    }
                }
            }
            charactersRow = new HashSet<>();
            charactersColumn = new HashSet<>();
        }
        return true;
    }
}