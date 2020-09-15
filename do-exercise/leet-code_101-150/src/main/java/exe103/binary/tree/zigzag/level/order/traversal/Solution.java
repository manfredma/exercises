package exe103.binary.tree.zigzag.level.order.traversal;

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return new ArrayList<>();
        }
        List<Integer> cur = new ArrayList<>();
        cur.add(root.val);
        result.add(cur);

        List<TreeNode> curNode = new ArrayList<>();
        curNode.add(root);
        boolean fromRight = true;

        do {
            List<TreeNode> nextNode = new ArrayList<>();
            List<Integer> next = new ArrayList<>();
            if (fromRight) {
                for (int i = 0; i < curNode.size(); i++) {
                    TreeNode treeNode = curNode.get(curNode.size() - 1 - i);
                    if (treeNode.right != null) {
                        next.add(treeNode.right.val);
                        nextNode.add(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        next.add(treeNode.left.val);
                        nextNode.add(treeNode.left);
                    }
                }
            } else {
                for (int i = 0; i < curNode.size(); i++) {
                    TreeNode treeNode = curNode.get(curNode.size() - 1 - i);
                    if (treeNode.left != null) {
                        next.add(treeNode.left.val);
                        nextNode.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        next.add(treeNode.right.val);
                        nextNode.add(treeNode.right);
                    }
                }
            }
            if (!next.isEmpty()) {
                result.add(next);
            }
            fromRight = !fromRight;
            curNode = nextNode;
        } while (!curNode.isEmpty());
        return result;
    }
}