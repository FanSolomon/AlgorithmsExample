package Basic_03;

/**
 * “之” 字形打印矩阵
 * 给定一个矩阵matrix， 按照“之” 字形的方式打印这个矩阵，
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12
 * “之” 字形打印的结果为： 1， 2， 5， 9， 6， 3， 4， 7， 10， 11，8， 12
 * 额外空间复杂度为O(1)
 */
public class Code_ZigZagPrintMatrix {

    /**
     * 定义A点B点都在左上角矩阵开始位置
     * A点(a,b)向右移动，到边界后向下
     * B点(c,d)向下移动，到边界后向右
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int endA = matrix[0].length - 1;
        int endB = matrix.length - 1;
        boolean flag = false;
        int nothing;
        while (a < endA) {
            printLevel(matrix, a, b, c, d, flag);
            flag = !flag;
            nothing = b < endA ? b++ : a++;
            nothing = c < endB ? c++ : d++;
        }
    }

    /**
     * 打印对角线上A(a,b),B(c,d)上的点
     * @param f 为false时，从B->A打印，为true时，从A->B打印
     */
    public static void printLevel(int[][] m, int a, int b, int c, int d, boolean f) {
        if (f) {
            while (a <= c) {
                System.out.print(m[a++][b--] + " ");
            }
        } else {
            while (a <= c)
            System.out.print(m[c--][d++] + " ");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);
    }
}
