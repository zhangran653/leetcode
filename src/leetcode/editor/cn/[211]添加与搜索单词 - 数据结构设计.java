package leetcode.editor.cn;//设计一个支持以下两种操作的数据结构：
//
// void addWord(word)
//bool search(word)
// 
//
// search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。 
//
// 示例: 
//
// addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
// 
//
// 说明: 
//
// 你可以假设所有单词都是由小写字母 a-z 组成的。 
// Related Topics 设计 字典树 回溯算法


import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {
    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node() {
            this.isWord = false;
            next = new TreeMap<>();
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
        }
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        if (word.charAt(index) != '.') {
            Character c = word.charAt(index);
            if (node.next.get(c) == null) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char c : node.next.keySet()) {
                if (match(node.next.get(c), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)
