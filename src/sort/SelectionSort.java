package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-04-30
 **/
public class SelectionSort {

    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n^2) | O(1)      | 不稳定
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
