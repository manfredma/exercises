package exe29.divide.two.integers;

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
            return 1;
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == 0) {
            return 0;
        }
        long result = 0;
        long x = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);
        long dividendPartial = 0;

        for (int i = 32; i > 0; i--) {
            long tmp = divisorL << (i - 1);
            while (tmp + dividendPartial <= x) {
                result += 1L << (i - 1);
                dividendPartial += tmp;
            }
        }
        if ((dividend ^ divisor) < 0) {
            return -(int) result;
        }
        return (int) result;
    }
}