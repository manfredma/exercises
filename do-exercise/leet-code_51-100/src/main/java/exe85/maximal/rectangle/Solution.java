package exe85.maximal.rectangle;

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] matrixToHistogram = new int[m][n];
        for (int i = 0; i < n; i++) {
            int maxOnes = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[j][i] == '1') {
                    maxOnes++;
                } else {
                    break;
                }
            }
            matrixToHistogram[0][i] = maxOnes;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != '0' && matrixToHistogram[i - 1][j] != 0) {
                    matrixToHistogram[i][j] = matrixToHistogram[i - 1][j] - 1;
                } else if (matrix[i][j] != '0' && matrixToHistogram[i - 1][j] == 0) {
                    int maxOnes = 0;
                    for (int k = i; k < m; k++) {
                        if (matrix[k][j] == '1') {
                            maxOnes++;
                        } else {
                            break;
                        }
                    }
                    matrixToHistogram[i][j] = maxOnes;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            result = Math.max(result, largestRectangleArea(matrixToHistogram[i]));
        }
        return result;
    }

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        heights = Arrays.copyOf(heights, heights.length + 1);
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int x = stack.pop();
                if (stack.isEmpty()) {
                    maxArea = Math.max(maxArea, i * heights[x]);
                } else {
                    maxArea = Math.max(maxArea, (i - stack.peek() - 1) * heights[x]);
                }
            }
        }
        return maxArea;
    }
}