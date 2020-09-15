package exe128.longest.consecutive.sequence;

import java.util.HashMap;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> n = new HashMap<>(nums.length);
        int r = 0;
        for (int num : nums) {
            if (n.containsKey(num)) {
                continue;
            }

            int left = n.getOrDefault(num - 1, 0);
            int right = n.getOrDefault(num + 1, 0);
            int sum = left + right + 1;

            n.put(num - left, sum);
            n.put(num + right, sum);
            // 加入number 是为了防止同 num 再进来
            n.put(num, sum);

            r = Math.max(r, sum);
        }
        return r;
    }
}