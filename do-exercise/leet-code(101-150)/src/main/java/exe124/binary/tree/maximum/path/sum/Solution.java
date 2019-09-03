package exe124.binary.tree.maximum.path.sum;

import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger result = new AtomicInteger(Integer.MIN_VALUE);
        findMax(root, result);
        return result.get();
    }

    private int findMax(TreeNode root, AtomicInteger result) {
        int x = 0;
        if (root.left != null) {
            x = findMax(root.left, result);
        }
        int y = 0;
        if (root.right != null) {
            y = findMax(root.right, result);
        }

        if (x + y + root.val > result.get()) {
            result.set(x + y + root.val);
        }
        return Math.max(0, Math.max(root.val + x, root.val + y));
    }
}