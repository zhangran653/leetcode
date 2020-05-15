package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-05-06
 **/
public class Test {

    /**
     * 重复的走过要访问的排序数列,每次比较2个元素，如果顺序错误就交换
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n)   | O(1)      | 稳定
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
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

    /**
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，
     * 然后放到已排序序列的末尾
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n^2) | O(1)      | 不稳定
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
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

    /**
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^2) |   O(n^2)| O(n)   | O(1)      | 稳定
     *
     * @param arr
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j;
            int temp = arr[i];
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    /**
     * 是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^1.3)|   O(n^2)| O(n)   | O(1)     | 不稳定
     *
     * @param arr
     */
    public void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j;
                int temp = arr[i];
                for (j = i; j - gap >= 0 && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }

    }

    private void merge(int[] arr, int l, int mid, int r) {
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
        new Test().mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
