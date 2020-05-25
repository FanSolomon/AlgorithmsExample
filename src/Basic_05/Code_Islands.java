package Basic_05;

/**
 * 岛问题
 * 一个矩阵中只有0和1两种值， 每个位置都可以和自己的上、 下、 左、 右
 * 四个位置相连， 如果有一片1连在一起， 这个部分叫做一个岛， 求一个
 * 矩阵中有多少个岛？
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0
 * 0 0 0 0 0 0
 * 这个矩阵中有三个岛。
 *
 * 原问题不需要并查集结构，用递归实现。
 * 但若矩阵特别大，如何多任务并行的方式解决该题，则要用到并查集
 */
public class Code_Islands {

    /**
     * 递归版本求岛数量
     * 从矩阵第一行依次遍历矩阵，遇到1就调用感染函数
     * 感染函数会递归地将1周围的所有1（也就是同一个岛）改为2
     * 所以后续遍历过程中遇到2就跳过
     * 执行一次1的感染，岛总数就加1
     */
    public static int countIslands(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int islandNum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    infect(matrix, i, j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    /**
     * 感染函数
     * 实现将一个岛上的1全部改为2
     */
    public static void infect(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] != 1) {
            return;
        }
        matrix[i][j] = 2;
        infect(matrix, i - 1, j);
        infect(matrix, i, j - 1);
        infect(matrix, i + 1, j);
        infect(matrix, i, j + 1);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m1));

        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                        { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                        { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                        { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println(countIslands(m2));

    }
}
