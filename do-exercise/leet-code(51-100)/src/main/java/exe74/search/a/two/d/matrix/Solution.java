package exe74.search.a.two.d.matrix;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        int totalItemSize = rowSize * columnSize;

        int begin = 0;
        int end = totalItemSize - 1;

        while (true) {
            int middle = (begin + end) / 2;

            int middleRow = middle / columnSize;
            int middleColumn = middle % columnSize;
            if (matrix[middleRow][middleColumn] == target) {
                return true;
            } else if (matrix[middleRow][middleColumn] < target) {
                begin = middle + 1;
            } else {
                end = middle - 1;
            }

            if (end < begin) {
                return false;
            }
        }
    }
}