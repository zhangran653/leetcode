package sort;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author zhangran
 * @since 2020-05-05
 **/
public class ShellSort {

    /**
     * 是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序
     * 时间复杂度 |（平均）| （最坏） |（最好）| 空间复杂度 | 稳定性
     * |         | O(n^1.3)|   O(n^2)| O(n)   | O(1)     | 不稳定
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        //希尔排序的增量,每次都/2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从增量那组开始进行插入排序，直至完毕
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j;
                // j - step 就是代表与它同组隔壁的元素
                for (j = i; j - gap >= 0 && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
        System.out.println(Arrays.toString(array));
        LocalDate d2 = LocalDate.now();
        System.out.println(d2.minusDays(92).toString());
    }


}
