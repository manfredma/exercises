package exe95.unique.binary.search.trees.ii;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (n == 0) {
            return treeNodes;
        }

        return doGenerateTrees(1, n);
    }

    private List<TreeNode> doGenerateTrees(int i, int n) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (i == n) {
            treeNodes.add(new TreeNode(i));
        } else if (i > n) {
            treeNodes.add(null);
        } else {
            for (int j = i; j <= n; j++) {
                List<TreeNode> left = doGenerateTrees(i, j - 1);
                List<TreeNode> right = doGenerateTrees(j + 1, n);
                for (TreeNode treeNode : left) {
                    for (TreeNode node : right) {
                        TreeNode root = new TreeNode(j);
                        root.left = treeNode;
                        root.right = node;
                        treeNodes.add(root);
                    }
                }
            }
        }
        return treeNodes;
    }
}