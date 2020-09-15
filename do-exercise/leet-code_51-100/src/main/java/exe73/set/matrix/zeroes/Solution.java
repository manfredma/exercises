package exe73.set.matrix.zeroes;

class Solution {
    public void setZeroes(int[][] matrix) {

        int row = matrix.length;
        if (row == 0) {
            return;
        }
        if (row == 1) {
            int[] firstRow = matrix[0];
            boolean hasZeroes = false;
            for (int i = 0; i < firstRow.length; i++) {
                if (firstRow[i] == 0) {
                    hasZeroes = true;
                    break;
                }
            }
            if (hasZeroes) {
                for (int i = 0; i < firstRow.length; i++) {
                    firstRow[i] = 0;
                }
            }
            return;
        }

        int n = matrix[0].length;
        if (n == 1) {
            boolean hasZeroes = false;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == 0) {
                    hasZeroes = true;
                    break;
                }
            }
            if (hasZeroes) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][0] = 0;
                }
            }
            return;
        }
        boolean firstColumnHasZeros = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnHasZeros = true;
                break;
            }
        }
        boolean firstRowHasZeroes = false;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZeroes = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 查看第一列的0值
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 查看第一行的0值
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (firstColumnHasZeros) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRowHasZeroes) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }
}