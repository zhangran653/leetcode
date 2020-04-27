package uf;

/**
 * @author zhangran
 * @since 2020-04-27
 **/
public class UnionFind implements UF {

    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int find(int p) {
        while (p != parent[p]) {
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
        count--;
    }
}
