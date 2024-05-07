package exe442.find.all.duplicates.in.an.array;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        // 利用符号记录是否已经查找到元素
        for (int i = 0; i < nums.length; i++) {
            int x = Math.abs(nums[i]);
            if (nums[x - 1] < 0) {
                result.add(x);
            } else {
                nums[x - 1] = -nums[x - 1];
            }
        }
        return result;
    }
}