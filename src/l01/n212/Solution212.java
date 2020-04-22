package l01.n212;//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution212 {
    class Trie {

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
        public Trie() {
            this.root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
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
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (cur.next.get(c) == null) {
                    return false;
                }
                cur = cur.next.get(c);
            }
            return cur.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                Character c = prefix.charAt(i);
                if (cur.next.get(c) == null) {
                    return false;
                }
                cur = cur.next.get(c);
            }
            return true;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        if (m == 0) {
            return new ArrayList<>();
        }
        int n = board[0].length;
        if (n == 0) {
            return new ArrayList<>();
        }
        // 构建字典树，将words加入
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(res, board, trie, "", i, j);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(Set<String> res, char[][] board, Trie trie, String prefix, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        String cur = prefix + board[x][y];
        if (!trie.startsWith(cur)) {
            return;
        }
        if (trie.search(cur)) {
            res.add(cur);
            //找到单词后不能return，还要继续往下
        }
        // 上下左右dfs
        char temp = board[x][y];
        // 替换，防止重复回来
        board[x][y] = '@';
        dfs(res, board, trie, cur, x - 1, y);
        dfs(res, board, trie, cur, x + 1, y);
        dfs(res, board, trie, cur, x, y - 1);
        dfs(res, board, trie, cur, x, y + 1);
        board[x][y] = temp;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
