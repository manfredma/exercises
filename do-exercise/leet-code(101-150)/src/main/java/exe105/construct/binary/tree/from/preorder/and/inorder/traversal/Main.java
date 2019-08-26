/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

 */
package exe105.construct.binary.tree.from.preorder.and.inorder.traversal;

/**
 * @author Manfred since 2019/8/26
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(root);
    }
}
