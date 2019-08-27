package exe107.binary.tree.level.order.traversal.ii;


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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> reverseResult = new ArrayList<>();
        if (null == root) {
            return new ArrayList<>();
        }
        List<Integer> cur = new ArrayList<>();
        cur.add(root.val);
        reverseResult.add(cur);

        List<TreeNode> curNode = new ArrayList<>();
        curNode.add(root);


        do {
            List<TreeNode> nextNode = new ArrayList<>();
            List<Integer> next = new ArrayList<>();
            for (TreeNode treeNode : curNode) {
                if (treeNode.left != null) {
                    next.add(treeNode.left.val);
                    nextNode.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    next.add(treeNode.right.val);
                    nextNode.add(treeNode.right);
                }
            }
            if (!next.isEmpty()) {
                reverseResult.add(next);
            }
            curNode = nextNode;
        } while (!curNode.isEmpty());
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < reverseResult.size(); i++) {
            result.add(reverseResult.get(reverseResult.size() - 1 - i));
        }
        return result;
    }
}