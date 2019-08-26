package exe32.longest.valid.parentheses;

import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        int result = 0;
        if (null == s || "".equals(s)) {
            return 0;
        }

        Stack<StackElement> characters = new Stack<>();
        boolean[] isValid = new boolean[s.length()];

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                characters.push(new StackElement(chars[i], i));
            } else {
                if (characters.size() == 0) {
                    characters.push(new StackElement(chars[i], i));
                    continue;
                }
                StackElement l = characters.peek();
                if (l.c == '(') {
                    characters.pop();
                    isValid[i] = true;
                    isValid[l.position] = true;
                } else {
                    characters.push(new StackElement(chars[i], i));
                }
            }
        }
        int tmp = 0;
        for (int i = 0; i < isValid.length; i++) {
            if (isValid[i]) {
                tmp++;
            } else {
                tmp = 0;
            }
            if (tmp > result) {
                result = tmp;
            }
        }
        return result;
    }

    private static class StackElement {
        private char c;
        private int position;

        public StackElement(char c, int p) {
            this.c = c;
            this.position = p;
        }
    }
}