package exe211.add.and.search.word.data.structure.design;

class WordDictionary {

    private TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {

    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.children[word.charAt(i) - 'a'] == null) {
                current.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            current = current.children[word.charAt(i) - 'a'];
        }
        current.isEnd = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        return search(word, root, 0);
    }

    private boolean search(String word, TrieNode node, int index) {
        char cur = word.charAt(index);
        if (index == word.length() - 1) {
            if (cur == '.') {
                for (TrieNode child : node.children) {
                    if (child != null && child.isEnd) {
                        return true;
                    }
                }
                return false;
            } else {
                if (node.children[cur - 'a'] != null && node.children[cur - 'a'].isEnd) {
                    return true;
                }
                return false;
            }
        }

        if (cur == '.') {
            for (TrieNode child : node.children) {
                if (child != null) {
                    if (search(word, child, index + 1)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            if (node.children[cur - 'a'] == null) {
                return false;
            } else {
                return search(word, node.children[cur - 'a'], index + 1);
            }
        }
    }

    private static class TrieNode {
        /**
         * 本节点是否是某个单次的结束
         */
        private boolean isEnd;

        /**
         * 子节点
         */
        private TrieNode[] children = new TrieNode[26];
    }
}

