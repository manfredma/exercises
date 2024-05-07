package exe139.wordbreak;

import java.util.List;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int wordDictLen = wordDict.size();
        boolean[] wordBreakCache = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < wordDictLen; j++) {
                if (i >= wordDict.get(j).length() - 1
                        && (i == wordDict.get(j).length() - 1 || wordBreakCache[i - wordDict.get(j).length()])
                        && s.substring(i - wordDict.get(j).length() + 1, i + 1).endsWith(wordDict.get(j))) {
                    wordBreakCache[i] = true;
                }
            }
        }
        return wordBreakCache[s.length() - 1];
    }
}