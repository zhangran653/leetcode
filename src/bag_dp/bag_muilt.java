package bag_dp;

/**
 * @author zhangran
 * @since 2020-04-17
 **/
public class bag_muilt {
    private int maxValue(int[] w, int[] v, int[] nums, int capacity) {
        int row = w.length + 1;
        int col = capacity + 1;
        int[][] result = new int[row][col];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (j < w[i - 1]) {
                    result[i][j] = result[i - 1][j];
                } else {
                    //准备放k件物品
                    int count = Math.min(nums[i - 1], j / w[i - 1]);
                    result[i][j] = Math.max(result[i - 1][j - count * w[i - 1]] + count * v[i - 1], result[i - 1][j]);
                }
            }
        }

        return result[row - 1][col - 1];
    }

}