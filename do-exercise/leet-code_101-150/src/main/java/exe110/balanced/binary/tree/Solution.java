package exe110.balanced.binary.tree;

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
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left - right > 1 || right - left > 1) {
            return false;
        }
        if (!isBalanced(root.right)) {
            return false;
        }
        return isBalanced(root.left);
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int childMaxDepth = 0;
        if (root.left == null) {
            childMaxDepth = maxDepth(root.right);
        } else if (root.right == null) {
            childMaxDepth = maxDepth(root.left);
        } else {
            childMaxDepth = Math.max(maxDepth(root.left), maxDepth(root.right));
        }
        return childMaxDepth + 1;
    }
}