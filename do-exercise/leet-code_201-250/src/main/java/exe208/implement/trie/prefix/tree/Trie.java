package exe208.implement.trie.prefix.tree;

class Trie {

    TrieNode root = new TrieNode();

    /**
     * Initialize your data structure here.
     */
    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
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
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.children[word.charAt(i) - 'a'] != null) {
                current = current.children[word.charAt(i) - 'a'];
            } else {
                return false;
            }
        }
        return current.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (current.children[prefix.charAt(i) - 'a'] != null) {
                current = current.children[prefix.charAt(i) - 'a'];
            } else {
                return false;
            }
        }
        return true;
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
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */