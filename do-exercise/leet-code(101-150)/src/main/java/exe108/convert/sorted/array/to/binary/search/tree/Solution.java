package exe108.convert.sorted.array.to.binary.search.tree;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);

        int[] l = new int[mid];
        System.arraycopy(nums, 0, l, 0, mid);
        root.left = sortedArrayToBST(l);

        int[] r = new int[nums.length - 1 - mid];
        System.arraycopy(nums, mid + 1, r, 0, nums.length - 1 - mid);
        root.right = sortedArrayToBST(r);
        return root;
    }
}