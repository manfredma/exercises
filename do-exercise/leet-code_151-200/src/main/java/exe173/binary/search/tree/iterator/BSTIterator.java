package exe173.binary.search.tree.iterator;


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
class BSTIterator {

    Stack<TreeNode> s = new Stack<>();

    public BSTIterator(TreeNode root) {
        if (null != root) {
            s.push(root);
            TreeNode cur = root.left;
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = s.pop();
        if (null != cur.right) {
            TreeNode tmp = cur.right;
            while (null != tmp) {
                s.push(tmp);
                tmp = tmp.left;
            }
        }
        return cur.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !s.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */