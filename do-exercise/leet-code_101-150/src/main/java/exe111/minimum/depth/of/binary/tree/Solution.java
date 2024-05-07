package exe111.minimum.depth.of.binary.tree;

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
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int childMinDepth = 0;
        if (root.left == null) {
            childMinDepth = minDepth(root.right);
        } else if (root.right == null) {
            childMinDepth = minDepth(root.left);
        } else {
            childMinDepth = Math.min(minDepth(root.left), minDepth(root.right));
        }
        return childMinDepth + 1;
    }
}