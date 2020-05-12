package Basic_01;

import java.util.Arrays;

/**
 * 随机快排
 * 利用荷兰国旗问题改进的快排
 * 只能通过长期期望算出时间复杂度
 * 长期期望：时间复杂度O(N*logN)
 *
 * 
 * 存储p所需要的空间，长期期望：额外空间复杂度O(logN)
 *
 * 在算法中，当样本不可控时：
 * 1.利用随机，打乱样本状况
 * 2.利用hash
 */
public class Code_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);  //加这一句成为随机快排 防止{6,5,4,3,2,1}这种情况复杂度会变成O(N^2)
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, L++, ++less);
            } else if (arr[L] > arr[R]) {
                swap(arr, L, --more);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[] {less+1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,5,2,7,1,2};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
