package exe140.wordbreak.ii;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        int wordDictLen = wordDict.size();
        boolean[] wordBreakCache = new boolean[s.length()];
        List<String>[] wordBreakResultCache = new List[s.length()];
        for (int i = 0; i < wordBreakResultCache.length; i++) {
            wordBreakResultCache[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < wordDictLen; j++) {
                String singleWordDict = wordDict.get(j);
                int singleWordDictLen = singleWordDict.length();
                if (i >= singleWordDictLen - 1
                        && (i == singleWordDictLen - 1 || wordBreakCache[i - singleWordDictLen])
                        && s.substring(i - singleWordDictLen + 1, i + 1).equals(singleWordDict)) {
                    wordBreakCache[i] = true;
                    if (i == singleWordDictLen - 1) {
                        wordBreakResultCache[i].add(singleWordDict);
                    } else {
                        for (int k = 0; k < wordBreakResultCache[i - singleWordDictLen].size(); k++) {
                            wordBreakResultCache[i].add(wordBreakResultCache[i - singleWordDictLen].get(k) + " " + singleWordDict);
                        }
                    }
                }
            }
        }
        return wordBreakResultCache[s.length() - 1];
    }
}