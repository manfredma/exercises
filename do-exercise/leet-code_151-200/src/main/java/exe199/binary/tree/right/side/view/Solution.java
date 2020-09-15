package exe199.binary.tree.right.side.view;

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
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        doRightSideView(root, 0, result);

        return result;
    }

    private void doRightSideView(TreeNode root, int i, List<Integer> result) {
        if (i == result.size()) {
            result.add(root.val);
        }

        if (root.right != null) {
            doRightSideView(root.right, i + 1, result);
        }
        if (root.left != null) {
            doRightSideView(root.left, i + 1, result);
        }
    }
}