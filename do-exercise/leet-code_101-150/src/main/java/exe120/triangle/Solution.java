package exe120.triangle;

import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            List<Integer> preRow = triangle.get(i - 1);
            for (int j = 0; j < row.size(); j++) {
                if (j == 0) {
                    row.set(j, row.get(j) + preRow.get(j));
                } else if (j == row.size() - 1) {
                    row.set(j, row.get(j) + preRow.get(j - 1));
                } else {
                    row.set(j, row.get(j) + Math.min(preRow.get(j - 1), preRow.get(j)));
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < triangle.get(triangle.size() - 1).size(); j++) {
            result = Math.min(triangle.get(triangle.size() - 1).get(j), result);
        }
        return result;
    }
}