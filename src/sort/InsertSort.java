package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-04-30
 **/
public class InsertSort {

    /**
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n)   | O(1)      | 稳定
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    /**
     * 改进
     *
     * @param arr
     */
    public void sort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 寻找啊arr[i] 合适的插入位置
            int temp = arr[i];
            // j保存temp该插入的位置
            int j;
            for (j = i; j -1>= 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }


    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new InsertSort().sort1(a);
        System.out.println(Arrays.toString(a));
    }

}
