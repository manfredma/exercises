package exe129.sum.root.to.leaf.numbers;

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
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        AtomicInteger sum = new AtomicInteger(0);
        doSumNumbers(root, 0, sum);
        return sum.get();
    }

    private void doSumNumbers(TreeNode root, int parent, AtomicInteger sum) {
        int cur = root.val + parent * 10;
        if (isLeaf(root)) {
            sum.set(sum.get() + cur);
        }

        if (root.left != null) {
            doSumNumbers(root.left, cur, sum);
        }
        if (root.right != null) {
            doSumNumbers(root.right, cur, sum);
        }
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}