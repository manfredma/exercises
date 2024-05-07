package exe38.count.and.say;

class Solution {
    public String countAndSay(int n) {
        String result = "";
        if (n == 0) {
            return result;
        }
        result =  "1";
        if (n == 1) {
            return result;
        }
        for (int i = 1; i < n; i++) {
            char[] chars = result.toCharArray();
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            char c = '0';
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == c) {
                    sum++;
                } else {
                    if (sum != 0) {
                        sb.append(sum).append(c);
                    }
                    c = chars[j];
                    sum = 1;
                }
            }
            if (sum != 0) {
                sb.append(sum).append(c);
            }
            result = sb.toString();
        }
        return result;
    }
}