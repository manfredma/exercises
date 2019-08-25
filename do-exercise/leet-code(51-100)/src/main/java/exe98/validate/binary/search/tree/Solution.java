package exe98.validate.binary.search.tree;

class Solution {
    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (root.left != null) {
            int maxLeft = root.left.val;
            TreeNode maxLeftNode = root.left;
            while (maxLeftNode.right != null) {
                maxLeftNode = maxLeftNode.right;
                maxLeft = maxLeftNode.val;
            }

            if (maxLeft >= root.val) {
                return false;
            }

            if (!isValidBST(root.left)) {
                return false;
            }
        }
        if (root.right != null) {
            int minRight = root.right.val;
            TreeNode minRightNode = root.right;
            while (minRightNode.left != null) {
                minRightNode = minRightNode.left;
                minRight = minRightNode.val;
            }

            if (minRight <= root.val) {
                return false;
            }
            if (!isValidBST(root.right)) {
                return false;
            }
        }
        return true;
    }
}