package rbtree;

import bst.BST;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author zhangran
 * @since 2020-04-26
 **/
public class BRTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {


        public K k;
        private V v;
        public Node left, right;
        public boolean color;

        public Node(K k, V v, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
            this.color = RED;
        }

        public Node(K k, V v) {
            this(k, v, null, null);
        }


    }

    private int size;
    private Node root;

    public BRTree() {
        size = 0;
        root = null;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //左旋转
    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //颜色反转
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //右旋转
    private Node rightRotate(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    public void add(K k, V v) {
        root = add(root, k, v);
        root.color = BLACK;

    }


    /**
     * 以node为根添加元素，返回添加后的根
     *
     * @param node
     * @param k
     * @return
     */
    private Node add(Node node, K k, V v) {
        if (node == null) {
            size++;
            // 默认插入红节点
            return new Node(k, v);
        }
        if (k.compareTo(node.k) < 0) {
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.k) > 0) {
            node.right = add(node.right, k, v);
        } else {
            node.v = v;
        }
        // 维护红黑树:
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    public boolean contains(K k) {
        return contains(root, k);
    }

    private boolean contains(Node node, K k) {
        if (node == null) {
            return false;
        }
        if (k.compareTo(node.k) == 0) {
            return true;
        } else if (k.compareTo(node.k) < 0) {
            return contains(node.left, k);
        } else {
            return contains(node.right, k);
        }
    }


    public V min() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return min(root).v;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public V max() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return max(root).v;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.left);
    }

    public V removeMax() {
        V e = max();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    public V removeMin() {
        V e = max();
        root = removeMin(root);
        return e;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K e) {
        return remove(root, e).v;
    }

    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.k) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.k) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;

            } else {
                Node successor = min(node.right);
                successor.left = node.left;
                successor.right = removeMin(node.right);
                node.left = null;
                node.right = null;
                return successor;
            }
        }
    }
}
