package exe222.count.complete.tree.nodes;

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHigh = getLeftHeight(root);
        int rightHigh = getRightHeight(root);
        if (leftHigh == rightHigh) {
            return (int)Math.pow(2, leftHigh) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    private int getLeftHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int high = 1;
        while (root.left != null) {
            root = root.left;
            high++;
        }
        return high;
    }

    private int getRightHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int high = 1;
        while (root.right != null) {
            root = root.right;
            high++;
        }
        return high;
    }
}