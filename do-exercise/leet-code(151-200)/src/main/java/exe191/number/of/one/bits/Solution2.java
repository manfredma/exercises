package exe191.number.of.one.bits;

class Solution2 {
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n &= (n - 1);
        }
        return result;
    }
}