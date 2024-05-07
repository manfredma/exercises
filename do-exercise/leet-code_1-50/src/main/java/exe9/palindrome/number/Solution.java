package exe9.palindrome.number;

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int remainder = x;
        int reverse = 0;
        while (remainder / 10 > 0) {
            reverse = reverse * 10 + remainder % 10;
            remainder = remainder / 10;
        }
        if (remainder > 0) {
            reverse = reverse * 10 + remainder;
        }
        return reverse == x;
    }
}