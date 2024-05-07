/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

 */
package exe129.sum.root.to.leaf.numbers;

/**
 * @author manfred on 2019/9/7.
 */
public class Main {
    public static void main(String[] args) {
        TreeNode treeNode11 = new TreeNode(1);
        TreeNode treeNode12 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(3);

        treeNode11.left = treeNode12;
        treeNode11.right = treeNode13;

        Solution solution = new Solution();
        System.out.println(solution.sumNumbers(treeNode11));

        TreeNode treeNode21 = new TreeNode(4);
        TreeNode treeNode22 = new TreeNode(9);
        TreeNode treeNode23 = new TreeNode(0);
        TreeNode treeNode24 = new TreeNode(5);
        TreeNode treeNode25 = new TreeNode(1);

        treeNode21.left = treeNode22;
        treeNode21.right = treeNode23;
        treeNode22.left = treeNode24;
        treeNode22.right = treeNode25;

        System.out.println(solution.sumNumbers(treeNode21));
    }
}
