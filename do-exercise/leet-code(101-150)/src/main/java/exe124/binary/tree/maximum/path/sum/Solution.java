package exe124.binary.tree.maximum.path.sum;

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

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        dispose(root);
        return findMax(root);

    }

    private int findMax(TreeNode root) {
        int result = Integer.MIN_VALUE;
        if (root.val > result) {
            result = root.val;
        }
        if (root.left != null) {
            int leftMax = findMax(root.left);
            if (leftMax > result) {
                result = leftMax;
            }
        }
        if (root.right != null) {
            int rightMax = findMax(root.right);
            if (rightMax > result) {
                result = rightMax;
            }
        }
        return result;
    }

    private void dispose(TreeNode root) {
        if (root.left != null) {
            dispose(root.left);
            if (root.left.val > 0) {
                root.val += root.left.val;
            }
        }
        if (root.right != null) {
            dispose(root.right);
            if (root.right.val > 0) {
                root.val += root.right.val;
            }
        }
    }
}