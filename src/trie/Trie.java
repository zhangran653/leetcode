package trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhangran
 * @since 2020-04-21
 **/
public class Trie {
    private class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            new Node(false);
        }

    }

    public Trie() {
        this.size = 0;
        this.root = new Node();
    }

    private int size;
    private Node root;


    public int getSize() {
        return size;
    }

    public void add(String s) {
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }
}
