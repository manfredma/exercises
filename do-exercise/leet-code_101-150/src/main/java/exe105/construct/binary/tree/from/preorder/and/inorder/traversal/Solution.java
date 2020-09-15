package exe105.construct.binary.tree.from.preorder.and.inorder.traversal;


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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        int indexInInorder = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                indexInInorder = i;
                break;
            }
        }
        int[] leftPreOrder = new int[indexInInorder];
        System.arraycopy(preorder, 1, leftPreOrder, 0, indexInInorder);
        int[] leftInOrder = new int[indexInInorder];
        System.arraycopy(inorder, 0, leftInOrder, 0, indexInInorder);
        root.left = buildTree(leftPreOrder, leftInOrder);

        int rightLength = preorder.length - 1 - indexInInorder;
        int[] rightPreOrder = new int[rightLength];
        System.arraycopy(preorder, indexInInorder + 1, rightPreOrder, 0, rightLength);
        int[] rightInOrder = new int[rightLength];
        System.arraycopy(inorder, indexInInorder + 1, rightInOrder, 0, rightLength);
        root.right = buildTree(rightPreOrder, rightInOrder);

        return root;
    }
}