package avl;

/**
 * @author zhangran
 * @since 2020-04-26
 **/
public class AvlTree<K extends Comparable<K>, V> {

    private class Node {
        public K k;
        public V v;
        public Node left, right;
        public int height;

        public Node(K k, V v, Node left, Node right) {
            this.k = k;
            this.v = v;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public Node(K k, V v) {
            this(k, v, null, null);
        }
    }

    private int size;
    private Node root;

    public AvlTree() {
        this.size = 0;
        this.root = null;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
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
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //右旋转
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            // 左旋转
            return leftRotate(node);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node node) {
        //右旋转
        Node left = node.left;
        Node leftR = left.right;
        left.right = node;
        node.left = leftR;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        left.height = 1 + Math.max(getHeight(left.left), getHeight(left.right));
        return left;
    }

    private Node leftRotate(Node node) {
        // 左旋转
        Node right = node.right;
        Node rightL = right.left;
        right.left = node;
        node.right = rightL;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        right.height = 1 + Math.max(getHeight(right.left), getHeight(right.right));
        return right;

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
