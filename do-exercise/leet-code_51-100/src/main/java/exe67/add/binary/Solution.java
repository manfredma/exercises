package exe67.add.binary;

class Solution {

    char charOfZero = '0';

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int m = a.length();
        int n = b.length();

        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < a.length() && i < b.length()) {
                int x = (a.charAt(a.length() - 1 - i) - charOfZero) + (b.charAt(b.length() - 1 - i) - charOfZero) + carry;
                sb.append(x % 2);
                carry = x / 2;
            } else if (i < a.length()) {
                int x = (a.charAt(a.length() - 1 - i) - charOfZero) + carry;
                sb.append(x % 2);
                carry = x / 2;
            } else {
                int x = (b.charAt(b.length() - 1 - i) - charOfZero) + carry;
                sb.append(x % 2);
                carry = x / 2;
            }

        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}