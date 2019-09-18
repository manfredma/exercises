package exe204.count.primes;

class Solution {
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        int[] x = new int[n];
        for (int i = 2; i < n; i++) {
            if (x[i] != -1) {
                x[i] = i;
                for (int j = i * 2; j < n; j += i) {
                    x[j] = -1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (x[i] > 0) {
                result++;
            }
        }

        return result;
    }
}