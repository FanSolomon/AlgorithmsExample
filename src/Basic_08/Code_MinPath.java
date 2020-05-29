package Basic_08;

/**
 * 给你一个二维数组，二维数组中每个数都是正数，要求从左上角走到
 * 右下角，每一步只能向右或向下，沿途经过的数字要累加起来，返回
 * 最小的路径和。
 *
 * 动态规划（空间换时间）。先写出一个尝试版本（暴力递归），然后改成动态规划
 * 有些方法改不出动态规划，如汉诺塔问题，因为它的解空间就是所有步骤
 * ，没有重复计算，所以改不出。
 */
public class Code_MinPath {

    /**
     * 递归暴力枚举尝试
     *
     * 递归过程中，产生了大量的重复解，即已经算过的有去算了一遍
     * 所以，可以记录到一个缓存中，不进行重复计算，即记忆化搜素
     *
     * 无后效性问题：与到达这个状态的路径没有关系，只要状态的参数定了，返回值一定确定
     * 有后效性问题：如汉诺塔问题、n皇后问题等
     *
     * 可以定义一个dp二维数组用来存放每个位置到右下角的最短路径和
     */
    public static int walk(int[][] matrix, int i, int j) {
        if (i >= matrix.length - 1 && j >= matrix[0].length - 1) {
            return matrix[i][j];
        }
        if (i >= matrix.length - 1) {
            return matrix[i][j] + walk(matrix, i + 1, j);
        }
        if (j >= matrix[0].length - 1) {
            return matrix[i][j] + walk(matrix, i, j + 1);
        }
        int right = walk(matrix, i, j+1);   // right 右边位置到右下角的最短路径和
        int down = walk(matrix, i + 1, j);  // down 下边位置到右下角的最短路径和
        return matrix[i][j] + Math.min(right, down);
    }

    public static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    public static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0 && j != 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}
