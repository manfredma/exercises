package exe222.count.complete.tree.nodes;

public class Main {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.left = new TreeNode();
        treeNode.right = new TreeNode();

        treeNode.left.left = new TreeNode();
        treeNode.left.right = new TreeNode();

        treeNode.right.left = new TreeNode();

        Solution solution = new Solution();
        System.out.println(solution.countNodes(treeNode));
    }
}
