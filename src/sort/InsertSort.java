package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-04-30
 **/
public class InsertSort {

    /**
     * 插入排序
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 优化
     *
     * @param arr
     */
    public void sort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;

            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new InsertSort().sort1(a);
        System.out.println(Arrays.toString(a));
    }

}
