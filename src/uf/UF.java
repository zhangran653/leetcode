package uf;

/**
 * @author zhangran
 * @since 2020-04-27
 **/
public interface UF {
    int count();

    int find(int p);

    void union(int p, int q);
}
