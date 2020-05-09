package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangran
 * @since 2020-05-09
 **/
public class QuickSort3 {
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 随机标定点
        Random ram = new Random();
        int rand = ram.nextInt(r - l + 1) + l;
        swap(arr, rand, l);

        int v = arr[l];
        // arr[l+1...lt] < v
        int lt = l;
        // arr[gt...r] >v
        int gt = r + 1;
        //arr[lt+1...i) == v
        int i = l + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, lt + 1, i);
                lt++;
                i++;
            } else if (arr[i] > v) {
                swap(arr, gt - 1, i);
                gt--;
            } else {
                i++;
            }
        }
        swap(arr, l, lt);
        quickSort(arr, l, lt - 1);
        quickSort(arr, gt, r);

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 5, 3, 4, 10};
        new QuickSort3().quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
