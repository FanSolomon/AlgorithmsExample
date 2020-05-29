package Basic_08;

/**
 * 母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只母牛，
 * 假设不会死，求N年后母牛的数量。
 *
 * F(n) = F(n-1) + F(n-3)
 * 今年    去年    新生牛
 * 时间复杂度：O(n)
 * 但存在一个O(logN)的算法，利用了线性代数中矩阵乘法的方式
 * 只要是这种严格的递推式都存在一个O(logN)的解。
 */
public class Code_Cow {

    public static int getSum(int count, int i) {
        if (i <= 1) {
            return count;
        }
        return getSum(count, i - 1) + getSum(count, i - 3);
    }

    public static void main(String[] args) {
        System.out.println(getSum(1,6));
    }
}
