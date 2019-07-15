package exe52.n.queens.ii;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int totalNQueens(int n) {
        return doSolveNQueens(n, 0, new ArrayList<>(), new ArrayList<>()).size();
    }

    private List<List<String>> doSolveNQueens(int length,
                                              int rowIndex,
                                              List<List<String>> result,
                                              List<String> currentResult) {
        for (int i = 0; i < length; i++) {
            boolean isValid = true;
            // 判断是否有在同一列上的
            for (String s : currentResult) {
                if (s.charAt(i) == 'Q') {
                    isValid = false;
                }
            }
            if (!isValid) {
                continue;
            }
            // 判断是否有在对角线上的
            for (int j = 0; j < currentResult.size(); j++) {
                if (Math.abs(j - rowIndex) == Math.abs(currentResult.get(j).indexOf("Q") - i)) {
                    isValid = false;
                }
            }

            if (isValid) {
                currentResult.add(setUpString(length, i));
                if (rowIndex == length - 1) {
                    result.add(new ArrayList<>(currentResult));
                    currentResult.remove(rowIndex);
                    break;
                } else {
                    doSolveNQueens(length, rowIndex + 1, result, currentResult);
                    // 已经判断完后，将当前行删除继续
                    currentResult.remove(rowIndex);
                }
            }
        }
        return result;
    }

    private String setUpString(int length, int setUpIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i != setUpIndex) {
                sb.append('.');
            } else {
                sb.append('Q');
            }
        }
        return sb.toString();
    }
}