package exe201.bitwise.and.of.numbers.range;

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) {
            return 0;
        } else if (m == n) {
            return m;
        }


        for (int toZero = 0, x = m; toZero < 32; toZero++) {

            if ((m >>> toZero & 1) == 0) {

                if ((1 << toZero) + x >= n) {
                    break;
                } else {
                    x = x + (1 << toZero);
                }
            } else {
                m = m & ~(1 << toZero);
            }

        }
        return m;
    }
}