package exe01._03.string.to.url.lcci;

class SolutionV2 {
    public String replaceSpaces(String S, int length) {
        int sp = length - 1;
        char[] ans = S.toCharArray();
        int s = S.length() - 1;

        while (sp >= 0) {
            if (ans[sp] == ' ') {
                ans[s--] = '0';
                ans[s--] = '2';
                ans[s--] = '%';
                sp--;
            } else {
                ans[s--] = ans[sp--];
            }
        }
        return new String(ans, s + 1, S.length() - s - 1);
    }
}