package exe70.climbing.stairs;

class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int pre = 2;
        int prePre = 1;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = prePre + pre;
            prePre = pre;
            pre = current;
        }
        return current;
    }
}