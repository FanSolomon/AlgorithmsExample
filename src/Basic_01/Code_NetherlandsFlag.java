package Basic_01;

import java.util.Arrays;

/**
 * 荷兰国旗问题
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，
 * 等于num的数放在数组的中间，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code_NetherlandsFlag {

    /**
     * @param arr 入参数组
     * @param L 左边界
     * @param R 右边界
     * @param p p就是num这个数
     * @return
     */
    public static int[] partition(int[] arr, int L, int R, int p) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < p) {
                swap(arr, L++, ++less);
            } else if (arr[L] > p) {
                swap(arr, L, --more);
            } else {
                L++;
            }
        }
        return new int[] {less+1, more-1}; //返回等于区域的位置范围
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,5,2,7,1,0};
        int[] res = partition(arr, 0, arr.length - 1, 3);
        System.out.println(Arrays.toString(arr));
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
