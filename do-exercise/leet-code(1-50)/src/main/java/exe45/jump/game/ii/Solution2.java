package exe45.jump.game.ii;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    public int jump(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return 0;
        }

        boolean[] jumpAll = new boolean[nums.length];

        List<Integer> s = new ArrayList<>(nums.length);
        s.add(nums.length - 1);
        jumpAll[nums.length - 1] = true;
        int jump = 0;
        while (true) {
            jump++;
            List<Integer> tmp = new ArrayList<>(nums.length);
            for (Integer target : s) {
                if (nums[0] >= target) {
                    return jump;
                }
                for (int i = 1; i < target; i++) {
                    if (jumpAll[i]) {
                        continue;
                    }
                    if (nums[i] >= (target - i)) {
                        tmp.add(i);
                        jumpAll[i] = true;
                    }
                }
            }
            s = tmp;
        }
    }
}