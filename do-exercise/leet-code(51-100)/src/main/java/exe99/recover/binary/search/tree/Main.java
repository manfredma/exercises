/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?

 */
package exe99.recover.binary.search.tree;

/**
 * @author manfred on 2019/8/25.
 */
public class Main {
    public static void main(String[] args) {
        TreeNode treeNode11 = new TreeNode(1);
        TreeNode treeNode12 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(3);

        treeNode11.left = treeNode13;
        treeNode13.right = treeNode12;

        new Solution().recoverTree(treeNode11);
        System.out.println(treeNode11.val);
        System.out.println(treeNode12.val);
        System.out.println(treeNode13.val);

        TreeNode treeNode21 = new TreeNode(1);
        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode23 = new TreeNode(3);
        TreeNode treeNode24 = new TreeNode(4);

        treeNode23.left = treeNode21;
        treeNode23.right = treeNode24;
        treeNode24.left = treeNode22;

        new Solution().recoverTree(treeNode23);
        System.out.println(treeNode21.val);
        System.out.println(treeNode22.val);
        System.out.println(treeNode23.val);
        System.out.println(treeNode24.val);
    }
}
