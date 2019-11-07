package exe217.contains.duplicate;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Set<Integer> has = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!has.add(num)) {
                return true;
            }
        }
        return false;
    }
}