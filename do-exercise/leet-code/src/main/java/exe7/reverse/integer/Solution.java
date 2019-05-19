package exe7.reverse.integer;

class Solution {
    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        boolean negative = x < 0;

        int absX = Math.abs(x);

        long result = 0;

        while (absX > 0) {
            int modulus = absX % 10;
            result = result * 10 + modulus;
            absX = absX / 10;
        }

        if (result > Integer.MAX_VALUE) {
            return 0;
        }

        return negative ? -(int)result : (int)result;
    }
}