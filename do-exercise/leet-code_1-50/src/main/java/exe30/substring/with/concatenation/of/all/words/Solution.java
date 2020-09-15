package exe30.substring.with.concatenation.of.all.words;

import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (null == s || "".equals(s) || null == words || words.length == 0) {
            return results;
        }

        int singleWordLength = words[0].length();
        // 找到各自在其中的位置
        Map<String, List<Integer>> word2position = new HashMap<>(words.length);
        Map<Integer, String> position2word = new HashMap<>(words.length);
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (word2position.containsKey(words[i])) {
                continue;
            }
            word2position.put(words[i], new ArrayList<>());
            int index = -1;
            do {
                index = s.indexOf(words[i], index + 1);
                if (index != -1) {
                    word2position.get(words[i]).add(index);
                    position2word.put(index, words[i]);
                    positions.add(index);
                } else {
                    break;
                }
            } while (true);
        }

        Integer[] positionsArray = positions.toArray(new Integer[]{});
        Arrays.sort(positionsArray);

        for (int i = 0; i < positionsArray.length - words.length + 1; i++) {
            boolean isContinue = true;
            // 判断是否存在连续
            List<Integer> continuePositions = new ArrayList<>();
            continuePositions.add(positionsArray[i]);
            for (int j = 1; j < words.length; j++) {
                if (position2word.containsKey(positionsArray[i] + j * singleWordLength)) {
                    continuePositions.add(positionsArray[i] + j * singleWordLength);
                } else {
                    isContinue = false;
                    break;
                }
            }
            if (!isContinue) {
                continue;
            }

            // 下标连续则判断是否包含所有words
            List<String> wordsCopy = new LinkedList<>(Arrays.asList(words));
            for (int j = 0; j < words.length; j++) {
                wordsCopy.remove(position2word.get(continuePositions.get(j)));
            }
            if (wordsCopy.size() == 0) {
                results.add(positionsArray[i]);
            }
        }
        return results;
    }
}