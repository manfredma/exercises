package exe151.reverse.words.in.a.string;

class Solution {
    public String reverseWords(String s) {
        String result = "";
        s = s.trim();
        String[] ss = s.split(" ");
        for (int i = 0; i < ss.length; i++) {
            if (ss[ss.length - 1 - i] != null && !"".equals(ss[ss.length - 1 - i])) {
                result += ss[ss.length - 1 - i];
                if (i != ss.length - 1) {
                    result += " ";
                }
            }

        }
        return result;
    }
}