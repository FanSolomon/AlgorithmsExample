package Basic_08;

/**
 * 背包问题
 *
 * 原问题：给你一个数组arr和一个正数aim。如果可以任意选择arr中的数字，
 * 能不能累加得到aim，返回true或者false。
 *
 * 跟Print_All_Subsquences基本一致，每个位置存在或不存在。并且递归过程中
 * 存在大量重复解，可以进行动态规划，先写出递归版本，然后改动态规划。
 *
 * 需要准备一个二维数组，行数是arr的length，列数是arr所有数加起来的和，
 * 则这个二维数组中可以放得下所有的true和false。然后套路从最后一行一列
 * 依次推二维数组中每个位置的true或者false。
 */
public class Code_Knapsack {

	public static int maxValue1(int[] c, int[] p, int bag) {
		return process1(c, p, 0, 0, bag);
	}

	public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
		if (alreadyweight > bag) {
			return 0;
		}
		if (i == weights.length) {
			return 0;
		}
		return Math.max(
				
				process1(weights, values, i + 1, alreadyweight, bag),
				
				values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));
	}

	public static int maxValue2(int[] c, int[] p, int bag) {
		int[][] dp = new int[c.length + 1][bag + 1];
		for (int i = c.length - 1; i >= 0; i--) {
			for (int j = bag; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + c[i] <= bag) {
					dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] c = { 3, 2, 4, 7 };
		int[] p = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue1(c, p, bag));
		System.out.println(maxValue2(c, p, bag));
	}

}
