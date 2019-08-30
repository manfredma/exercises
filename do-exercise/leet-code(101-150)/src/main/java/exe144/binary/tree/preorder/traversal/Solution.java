package exe144.binary.tree.preorder.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<StackNode> stack = new Stack<>();
        stack.push(new StackNode(root));
        while (!stack.isEmpty()) {
            StackNode cur = stack.pop();
            result.add(cur.treeNode.val);
            if (cur.isLeft && cur.treeNode.left != null) {
                cur.isLeft = false;
                stack.push(new StackNode(cur.treeNode.left));
            }
            stack.push(cur);
        }
        return result;
    }

    private static class StackNode {
        private TreeNode treeNode;
        private boolean isLeft = true;

        public StackNode(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }
}