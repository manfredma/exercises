package exe99.recover.binary.search.tree;

class Solution {
    public void recoverTree(TreeNode root) {
        doRecoverTree(root);
    }

    private boolean doRecoverTree(TreeNode root) {
        TreeNode leftBiggest = null;
        if (root.left != null) {
            leftBiggest = findBiggest(root.left, root.left);
        }
        TreeNode rightSmallest = null;
        if (root.right != null) {
            rightSmallest = findSmallest(root.right, root.right);
        }

        if (leftBiggest != null && leftBiggest.val > root.val) {
            if (rightSmallest != null && rightSmallest.val < root.val) {
                int tmp = leftBiggest.val;
                leftBiggest.val = rightSmallest.val;
                rightSmallest.val = tmp;
                return true;
            } else {
                int tmp = leftBiggest.val;
                leftBiggest.val = root.val;
                root.val = tmp;
                return true;
            }
        } else if (rightSmallest != null && rightSmallest.val < root.val) {
            int tmp = rightSmallest.val;
            rightSmallest.val = root.val;
            root.val = tmp;
        } else {
            if (root.left != null) {
                boolean lr = doRecoverTree(root.left);
                if (lr) {
                    return true;
                }
            }
            if (root.right != null) {
                boolean rr = doRecoverTree(root.right);
                if (rr) {
                    return true;
                }
            }
        }
        return false;
    }

    private TreeNode findBiggest(TreeNode r, TreeNode candidate) {
        if (r.val > candidate.val) {
            candidate = r;
        }
        if (r.left != null) {
            candidate = findBiggest(r.left, candidate);
        }

        if (r.right != null) {
            candidate = findBiggest(r.right, candidate);
        }
        return candidate;
    }

    private TreeNode findSmallest(TreeNode r, TreeNode candidate) {
        if (r.val < candidate.val) {
            candidate = r;
        }
        if (r.left != null) {
            candidate = findSmallest(r.left, candidate);
        }

        if (r.right != null) {
            candidate = findSmallest(r.right, candidate);
        }
        return candidate;
    }
}