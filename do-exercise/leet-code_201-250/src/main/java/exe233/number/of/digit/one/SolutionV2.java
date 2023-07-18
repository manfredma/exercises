package exe233.number.of.digit.one;

class SolutionV2 {

    public int countDigitOne(int n) {

        long mulk = 1;
        int ans = 0;

        for (int k = 0; n >= mulk; k++) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }

}