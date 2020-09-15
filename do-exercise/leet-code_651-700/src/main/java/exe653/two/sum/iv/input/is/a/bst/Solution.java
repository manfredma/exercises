package exe653.two.sum.iv.input.is.a.bst;

import java.util.HashSet;
import java.util.Set;

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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        Set<Integer> elements = new HashSet<>();
        return midTraversal(root, elements, k);
    }

    private boolean midTraversal(TreeNode root, Set<Integer> elements, int k) {
        if (root.left != null) {
            boolean left = midTraversal(root.left, elements, k);
            if (left) {
                return true;
            }
        }
        if (elements.contains(k - root.val)) {
            return true;
        }
        elements.add(root.val);
        if (root.right != null) {
            return midTraversal(root.right, elements, k);
        }
        return false;

    }
}