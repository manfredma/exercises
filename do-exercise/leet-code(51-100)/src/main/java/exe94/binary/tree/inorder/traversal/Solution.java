package exe94.binary.tree.inorder.traversal;

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }

        doInorderTraversal(root, result);
        return result;
    }

    private void doInorderTraversal(TreeNode node, List<Integer> result) {
        if (null != node) {
            doInorderTraversal(node.left, result);
            result.add(node.val);
            doInorderTraversal(node.right, result);
        }
    }
}