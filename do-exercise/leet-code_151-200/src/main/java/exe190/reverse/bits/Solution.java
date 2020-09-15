package exe190.reverse.bits;

class Solution {
    public int reverseBits(int n) {
        int result = 0;
        int x = 0x1;
        for (int i = 0; i < 32; i++) {
            int ni = n & (x << i);
            if (i < 16) {
                result = result ^ (ni << (31 - 2 * i));
            } else {
                result = result ^ (ni >>> (2 * i - 31));
            }
        }
        return result;
    }
}