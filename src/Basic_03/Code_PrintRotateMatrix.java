package Basic_03;

/**
 * 转圈打印矩阵
 * 给定一个整型矩阵matrix， 请按照转圈的方式打印它。
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 打印结果为： 1， 2， 3， 4， 8， 12， 16， 15， 14， 13， 9，5， 6， 7， 11， 10
 * 额外空间复杂度为O(1)
 */
public class Code_PrintRotateMatrix {

    /**
     * (a,b) 为左上角点的坐标
     * (c,d) 为右下角点的坐标
     */
    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int rowNum = c - a;
        int columnNum = d - b;
        int i;
        for (i = 0; i < columnNum; i++) {
            System.out.println(m[a][b+i]);
        }
        for (i = 0; i < rowNum; i++) {
            System.out.println(m[a+i][d]);
        }
        for (i = 0; i < columnNum; i++) {
            System.out.println(m[c][d-i]);
        }
        for (i = 0; i < rowNum; i++) {
            System.out.println(m[c-i][b]);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotate(matrix);
    }
}
