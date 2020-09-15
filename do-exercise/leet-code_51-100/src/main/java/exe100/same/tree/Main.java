/*
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false

 */
package exe100.same.tree;

/**
 * @author manfred on 2019/8/25.
 */
public class Main {
    public static void main(String[] args) {
        TreeNode treeNode11 = new TreeNode(1);
        TreeNode treeNode12 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(3);
        treeNode11.left = treeNode12;
        treeNode11.right = treeNode13;

        System.out.println(new Solution().isSameTree(treeNode11, treeNode11));

        TreeNode treeNode21 = new TreeNode(1);
        TreeNode treeNode22 = new TreeNode(2);
        treeNode21.left = treeNode22;

        TreeNode treeNode31 = new TreeNode(1);
        TreeNode treeNode32 = new TreeNode(2);
        treeNode31.right = treeNode32;

        System.out.println(new Solution().isSameTree(treeNode31, treeNode32));

        TreeNode treeNode41 = new TreeNode(1);
        TreeNode treeNode42 = new TreeNode(2);
        TreeNode treeNode43 = new TreeNode(1);
        treeNode41.left = treeNode42;
        treeNode41.right = treeNode43;

        TreeNode treeNode51 = new TreeNode(1);
        TreeNode treeNode52 = new TreeNode(2);
        TreeNode treeNode53 = new TreeNode(1);
        treeNode51.left = treeNode53;
        treeNode51.right = treeNode52;

        System.out.println(new Solution().isSameTree(treeNode41, treeNode51));
    }
}
