package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-05-04
 **/
public class BubboSort {


    /**
     * 重复的走过要访问的排序数列,每次比较2个元素，如果顺序错误就交换
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n)   | O(1)      | 稳定
     *
     * @param arr
     */
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new BubboSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
