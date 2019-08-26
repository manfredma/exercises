package exe106.construct.binary.tree.from.inorder.and.postorder.traversal;

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int indexInInorder = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                indexInInorder = i;
                break;
            }
        }
        int[] leftPostOrder = new int[indexInInorder];
        System.arraycopy(postorder, 0, leftPostOrder, 0, indexInInorder);
        int[] leftInOrder = new int[indexInInorder];
        System.arraycopy(inorder, 0, leftInOrder, 0, indexInInorder);
        root.left = buildTree(leftInOrder, leftPostOrder);

        int rightLength = postorder.length - 1 - indexInInorder;
        int[] rightPostOrder = new int[rightLength];
        System.arraycopy(postorder, indexInInorder, rightPostOrder, 0, rightLength);
        int[] rightInOrder = new int[rightLength];
        System.arraycopy(inorder, indexInInorder + 1, rightInOrder, 0, rightLength);
        root.right = buildTree(rightInOrder, rightPostOrder);

        return root;
    }
}