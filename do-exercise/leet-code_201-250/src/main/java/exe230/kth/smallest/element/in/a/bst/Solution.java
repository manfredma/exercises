package exe230.kth.smallest.element.in.a.bst;

import java.util.ArrayList;
import java.util.List;


class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> sortedValue = new ArrayList<>(k);
        collectKValueSorted(root, sortedValue, k);
        return sortedValue.get(k - 1);
    }

    private void collectKValueSorted(TreeNode node, List<Integer> sortedValue, int k) {
        if (node == null) {
            return;
        }
        collectKValueSorted(node.left, sortedValue, k);
        sortedValue.add(node.val);
        if (sortedValue.size() >= k) {
            return;
        }
        collectKValueSorted(node.right, sortedValue, k);
    }
}