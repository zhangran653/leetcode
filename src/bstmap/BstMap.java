package bstmap;

/**
 * @author zhangran
 * @since 2020-04-26
 **/
public class BstMap<K extends Comparable<K>, V> {

    private class Node {
        public K k;
        public V v;
        public Node left, right;

        public Node(K k, V v, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
        }

        public Node(K k, V v) {
            this(k, v, null, null);
        }
    }

    private int size;
    private Node root;

    public BstMap() {
        this.size = 0;
        this.root = null;
    }

    public void add(K k, V v) {
        add(root, k, v);
    }

    private Node add(Node node, K k, V v) {
        if (node == null) {
            size++;
            return new Node(k, v);
        }
        if (k.compareTo(node.k) < 0) {
            node.left = add(node.left, k, v);
        } else if (k.compareTo(node.k) > 0) {
            node.right = add(node.right, k, v);
        } else {
            node.v = v;
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
        if (node.k.compareTo(k) == 0) {
            return true;
        } else if (node.k.compareTo(k) < 0) {
            return contains(node.right, k);
        } else {
            return contains(node.left, k);
        }
    }

    public V min() {
        if (root == null) {
            throw new IllegalArgumentException("bst enmpty");
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
        if (root == null) {
            throw new IllegalArgumentException("bst enmpty");
        }
        return max(root).v;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return min(node.right);
    }

    public V removeMax() {
        V max = max();
        removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public V removeMin() {
        V min = min();
        removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMax(node.left);
        return node;
    }

    public V remove(K k) {
        if (root == null) {
            return null;
        }
        Node removed = remove(root, k);
        return removed == null ? null : removed.v;
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
                Node right = node.right;
                node.right = null;
                size--;
                return right;
            } else if (node.right == null) {
                Node left = node.left;
                node.left = null;
                size--;
                return left;
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
