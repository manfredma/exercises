package exe96.unique.binary.search.trees;

import exe95.unique.binary.search.trees.ii.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }

        return doGenerateTrees(1, n);
    }

    private int doGenerateTrees(int i, int n) {
        int result = 0;
        List<TreeNode> treeNodes = new ArrayList<>();
        if (i >= n) {
            result = 1;
        } else {
            for (int j = i; j <= n; j++) {
                int left = doGenerateTrees(i, j - 1);
                int right = doGenerateTrees(j + 1, n);
                result += left * right;
            }
        }
        return result;
    }
}