package sort;

import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-05-17
 **/
public class HeapSort {

    public void heapSort(int[] arr) {
        heapify(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);
        }
    }

    private void heapify(int[] arr) {
        int k = parent(arr.length - 1);
        while (k >= 0) {
            shiftDown(arr, arr.length, k);
            k--;
        }
    }

    private void shiftDown(int[] arr, int size, int index) {

        while (leftChild(index) < size) {
            int left = leftChild(index);
            int right = rightChild(index);
            int j = leftChild(index);
            if (right < size && arr[right] > arr[left]) {
                j = rightChild(index);
            }
            if (arr[index] >= arr[j]) {
                break;
            }
            swap(arr, index, j);
            index = j;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 65, 1, 6, 32, 74, 23, 5};
        new HeapSort().heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
