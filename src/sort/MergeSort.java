package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-05-05
 **/
public class MergeSort {

    /**
     * @param arr
     */
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 递归使用归并排序，对arr[l...r]的范围进行排序
     *
     * @param arr
     * @param l
     * @param r
     */
    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;

        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        // 如果已经有序，则不用再merge了
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }

    }

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private void merge(int[] arr, int l, int mid, int r) {
        // 辅助数组
        int[] a = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            a[i - l] = arr[i];
        }
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = a[j - l];
                j++;
            } else if (j > r) {
                arr[k] = a[i - l];
                i++;
            } else if (a[i - l] < a[j - l]) {
                arr[k] = a[i - l];
                i++;
            } else {
                arr[k] = a[j - l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new MergeSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
