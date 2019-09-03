package exe126.word.ladder.ii;

import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }
        wordList.remove(beginWord);
        Set<Integer> integers = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(Collections.singletonList(beginWord)));

        while (true) {
            boolean found = false;
            List<List<String>> newResult = new ArrayList<>();
            Set<Integer> curTraversal = new HashSet<>();
            for (int i = 0; i < result.size(); i++) {
                String from = result.get(i).get(result.get(i).size() - 1);
                for (int j = 0; j < wordList.size(); j++) {
                    if (integers.contains(j)) {
                        continue;
                    }
                    String w = wordList.get(j);
                    if (canTransformOnce(from, w)) {
                        curTraversal.add(j);
                        List<String> newR = new ArrayList<>(result.get(i));
                        newR.add(w);
                        newResult.add(newR);
                        if (w.equals(endWord)) {
                            found = true;
                        }
                    }

                }
            }
            integers.addAll(curTraversal);
            result = newResult;
            if (found) {
                break;
            }
            if (result.isEmpty()) {
                break;
            }
        }
        result.removeIf(s -> !s.get(s.size() - 1).equals(endWord));
        return result;

    }

    private boolean canTransformOnce(String from, String to) {
        int c = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                c++;
            }
            if (c > 1) {
                break;
            }
        }
        return c == 1;
    }
}