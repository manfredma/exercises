/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ['5','3','.','.','7','.','.','.','.'],
  ['6','.','.','1','9','5','.','.','.'],
  ['.','9','8','.','.','.','.','6','.'],
  ['8','.','.','.','6','.','.','.','3'],
  ['4','.','.','8','.','3','.','.','1'],
  ['7','.','.','.','2','.','.','.','6'],
  ['.','6','.','.','.','.','2','8','.'],
  ['.','.','.','4','1','9','.','.','5'],
  ['.','.','.','.','8','.','.','7','9']
]
Output: true
Example 2:

Input:
[
  ['8','3','.','.','7','.','.','.','.'],
  ['6','.','.','1','9','5','.','.','.'],
  ['.','9','8','.','.','.','.','6','.'],
  ['8','.','.','.','6','.','.','.','3'],
  ['4','.','.','8','.','3','.','.','1'],
  ['7','.','.','.','2','.','.','.','6'],
  ['.','6','.','.','.','.','2','8','.'],
  ['.','.','.','4','1','9','.','.','5'],
  ['.','.','.','.','8','.','.','7','9']
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
 */
package exe36.valid.sudoku;

public class Main {
    public static void main(String[] args) {
        char[][] chars = new char[9][];
        chars[0] = new char[]{'5','3','.','.','7','.','.','.','.'};
        chars[1] = new char[]{'6','.','.','1','9','5','.','.','.'};
        chars[2] = new char[]{'.','9','8','.','.','.','.','6','.'};
        chars[3] = new char[]{'8','.','.','.','6','.','.','.','3'};
        chars[4] = new char[]{'4','.','.','8','.','3','.','.','1'};
        chars[5] = new char[]{'7','.','.','.','2','.','.','.','6'};
        chars[6] = new char[]{'.','6','.','.','.','.','2','8','.'};
        chars[7] = new char[]{'.','.','.','4','1','9','.','.','5'};
        chars[8] = new char[]{'.','.','.','.','8','.','.','7','9'};

        Solution solution = new Solution();
        System.out.println(solution.isValidSudoku(chars));

        chars[0] = new char[]{'8','3','.','.','7','.','.','.','.'};
        System.out.println(solution.isValidSudoku(chars));

    }
}
