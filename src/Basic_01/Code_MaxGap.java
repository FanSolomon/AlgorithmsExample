package Basic_01;

/**
 * 给定一个数组，求如果排序之后，相邻两数的最大差值，
 * 要求时间复杂度O(N)，且要求不能用非基于比较的排序。
 */
public class Code_MaxGap {

    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        boolean[] hasNum = new boolean[arr.length + 1];
        int[] mins = new int[arr.length + 1];
        int[] maxs = new int[arr.length + 1];
        int bid = 0;    //桶号
        for (int i = 0; i < len; i++) {
            bid = bucket(arr[i], len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    public static int bucket(int num, int len, int min, int max) {
        return (int) ((num - min) * len / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = {1,4,55,3,5,2,7,1,2};
        System.out.println(maxGap(arr));
    }
}
