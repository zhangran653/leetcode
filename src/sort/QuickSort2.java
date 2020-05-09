package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangran
 * @since 2020-05-09
 **/
public class QuickSort2 {

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    private int partition(int[] arr, int l, int r) {
        // 随机标定点
        Random ram = new Random();
        int rand = ram.nextInt(r - l + 1) + l;
        swap(arr, rand, l);

        int v = arr[l];
        // arr[l+1...i)<=v,arr(j...r]>=v
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (r >= l + 1 && arr[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        new QuickSort2().quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
