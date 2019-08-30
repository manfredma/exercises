package exe202.happy.number;

class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        while (fast != 1) {
            slow = next(slow);
            fast = next(fast);
            if (fast == 1) {
                return true;
            }
            fast = next(fast);
            if (fast != 1 && fast == slow) {
                return false;
            }
        }
        return true;
    }

    private int next(int n) {
        int result = 0;
        while (n != 0) {
            result += (n % 10) * (n % 10);
            n = n / 10;
        }
        return result;
    }
}