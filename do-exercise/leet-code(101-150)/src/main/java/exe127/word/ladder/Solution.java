package exe127.word.ladder;

import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Node start = createGraph(beginWord, wordList);
        start.distance = 1;
        start.color = 1;
        Queue<Node> nodes = new ArrayDeque<>(wordList.size());
        nodes.add(start);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            List<Node> neighbors = node.neighbors;
            for (int i = 0; i < neighbors.size(); i++) {
                if (neighbors.get(i).val.equals(endWord)) {
                    return node.distance + 1;
                }
                if (neighbors.get(i).color == 0) {
                    neighbors.get(i).distance = node.distance + 1;
                    nodes.add(neighbors.get(i));
                    neighbors.get(i).color = 1;
                }
            }
            node.color = 2;
        }

        return 0;
    }

    private Node createGraph(String beginWord, List<String> wordList) {
        Node node = new Node(beginWord);
        List<Node> otherNodes = new ArrayList<>(wordList.size());
        for (int i = 0; i < wordList.size(); i++) {
            String wordVal = wordList.get(i);
            otherNodes.add(new Node(wordVal));
            if (canTransformOnce(node.val, wordList.get(i))) {
                node.neighbors.add(otherNodes.get(i));
            }
        }

        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (canTransformOnce(wordList.get(i), wordList.get(j))) {
                    otherNodes.get(i).neighbors.add(otherNodes.get(j));
                    otherNodes.get(j).neighbors.add(otherNodes.get(i));
                }
            }
        }
        return node;
    }

    static class Node {

        private String val;

        private int distance = Integer.MAX_VALUE;

        /**
         * color : 0 -- 白色 未探测； 1 -- 灰色 压入队列中； 2 -- 黑色 已出队列
         */
        private int color = 0;

        List<Node> neighbors = new ArrayList<>();

        Node(String val) {
            this.val = val;
        }
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