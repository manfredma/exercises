package exe101.symmetric.tree;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        return doIsSymmetric(root, root);
    }

    private boolean doIsSymmetric(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        if (!doIsSymmetric(p.left, q.right)) {
            return false;
        }
        return doIsSymmetric(p.right, q.left);
    }
}