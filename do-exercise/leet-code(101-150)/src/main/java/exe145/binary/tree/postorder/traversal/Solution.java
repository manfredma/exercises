package exe145.binary.tree.postorder.traversal;

import java.util.ArrayList;
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Stack<StackNode> stack = new Stack<>();
        stack.push(new StackNode(root));
        while (!stack.isEmpty()) {
            StackNode cur = stack.peek();
            if (cur.state == 0) {
                cur.state = 1;
                if (cur.treeNode.left != null) {
                    stack.push(new StackNode(cur.treeNode.left));
                }
            } else if (cur.state == 1) {
                cur.state = 2;
                if (cur.treeNode.right != null) {
                    stack.push(new StackNode(cur.treeNode.right));
                }
            } else {
                stack.pop();
                result.add(cur.treeNode.val);
            }
        }
        return result;
    }

    private static class StackNode {
        private TreeNode treeNode;
        // 0 - 左子树未处理
        // 1 - 左子树已处理，右子树未处理
        // 2 - 左右子树均已处理
        private int state = 0;

        public StackNode(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }
}