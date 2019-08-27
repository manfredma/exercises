package exe112.path.sum;

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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (null == root) {
            return false;
        }

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
        }

        if (root.left != null) {
            root.left.val = root.val + root.left.val;
            if (hasPathSum(root.left, sum)) {
                return true;
            }
        }
        if (root.right != null) {
            root.right.val = root.val + root.right.val;
            if (hasPathSum(root.right, sum)) {
                return true;
            }
        }
        return false;
    }
}