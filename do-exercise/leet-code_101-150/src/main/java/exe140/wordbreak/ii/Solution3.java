package exe140.wordbreak.ii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution3 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        return doWordBreak(s, wordDict, new HashMap<>());
    }

    private List<String> doWordBreak(String s, List<String> wordDict, Map<String, List<String>> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < wordDict.size(); i++) {
            if (s.startsWith(wordDict.get(i))) {
                if (s.length() > wordDict.get(i).length()) {
                    List<String> sub = doWordBreak(s.substring(wordDict.get(i).length()), wordDict, cache);
                    for (String s1 : sub) {
                        result.add(wordDict.get(i) + " " + s1);
                    }
                } else {
                    result.add(wordDict.get(i));
                }
            }
        }
        cache.put(s, result);
        return result;
    }
}