package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-04-30
 **/
public class SelectionSort {

    /**
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     * 不稳定
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new SelectionSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
