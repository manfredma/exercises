package exe113.path.sum.ii;

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        doPathSum(root, sum, new ArrayList<>(), result, 0);
        return result;
    }

    private void doPathSum(TreeNode treeNode, int sum, List<Integer> path, List<List<Integer>> result, int preSum) {
        path.add(treeNode.val);
        int curSum = treeNode.val + preSum;
        if (treeNode.left == null && treeNode.right == null) {
            if (curSum == sum) {
                result.add(new ArrayList<>(path));
            }
        }
        if (treeNode.left != null) {
            doPathSum(treeNode.left, sum, path, result, curSum);
        }
        if (treeNode.right != null) {
            doPathSum(treeNode.right, sum, path, result, curSum);
        }
        // 出栈
        path.remove(path.size() - 1);
    }
}