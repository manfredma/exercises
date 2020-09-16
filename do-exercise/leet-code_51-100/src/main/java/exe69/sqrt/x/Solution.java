package exe69.sqrt.x;

class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        return doMySqrt(x, x);
    }

    private int doMySqrt(int x, int sqrt) {
        while (true) {
            if (sqrt > x / sqrt) {
                sqrt = sqrt / 2;
            } else if (sqrt == x / sqrt) {
                return sqrt;
            } else {
                break;
            }
        }

        while (true) {
            if ((sqrt + 1) > x / (sqrt + 1)) {
                return sqrt;
            } else {
                sqrt++;
            }
        }
    }
}