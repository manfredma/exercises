/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

 */
package exe124.binary.tree.maximum.path.sum;

/**
 * @author Manfred since 2019/8/28
 */
public class Main {
    public static void main(String[] args) {
        TreeNode treeNode41 = new TreeNode(-3);
        System.out.println(new Solution().maxPathSum(treeNode41));

        /* [5,4,8,11,null,13,4,7,2,null,null,null,1] */
        TreeNode treeNode = createTreeNode("[5,4,8,11,null,13,4,7,2,null,null,null,1]");
        System.out.println(new Solution().maxPathSum(treeNode));


        /* [1,-2,-3,1,3,-2,null,-1] */
        TreeNode treeNode31 = new TreeNode(1);
        TreeNode treeNode32 = new TreeNode(-2);
        TreeNode treeNode33 = new TreeNode(-3);
        TreeNode treeNode34 = new TreeNode(1);
        TreeNode treeNode35 = new TreeNode(3);
        TreeNode treeNode36 = new TreeNode(-2);
        TreeNode treeNode37 = new TreeNode(-1);

        treeNode31.left = treeNode32;
        treeNode31.right = treeNode33;

        treeNode32.left = treeNode34;
        treeNode32.right = treeNode35;

        treeNode33.left = treeNode36;

        treeNode34.left = treeNode37;

        System.out.println(new Solution().maxPathSum(treeNode31));


        TreeNode treeNode1 = new TreeNode(-10);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;

        System.out.println(new Solution().maxPathSum(treeNode1));

        TreeNode treeNode21 = new TreeNode(1);
        TreeNode treeNode22 = new TreeNode(2);
        TreeNode treeNode23 = new TreeNode(3);
        treeNode21.left = treeNode22;
        treeNode21.right = treeNode23;

        System.out.println(new Solution().maxPathSum(treeNode21));


    }

    private static TreeNode createTreeNode(String s) {
        s = s.replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "");
        String[] treeNode = s.split(",");
        TreeNode[] treeNodes = new TreeNode[treeNode.length];
        for (int i = 0; i < treeNode.length; i++) {
            if (!"null".equals(treeNode[i])) {
                treeNodes[i] = new TreeNode(Integer.parseInt(treeNode[i]));
            }
        }
        for (int i = 0; i < treeNodes.length; i++) {
            if (treeNodes[i] != null) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                if (left < treeNodes.length) {
                    treeNodes[i].left = treeNodes[left];
                }
                if (right < treeNodes.length) {
                    treeNodes[i].right = treeNodes[right];
                }
            }
        }
        return treeNodes[0];
    }
}
