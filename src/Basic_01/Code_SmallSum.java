package Basic_01;

/**
 * 小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * [1,3,4,2,5]
 * 小和为1+1+3+1+1+3+4+2=16
 *
 * 利用归并排序思想
 */
public class Code_SmallSum {

    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return mergeSort(arr, L, mid)
                + mergeSort(arr, mid + 1, R)
                + merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int a = L;
        int b = mid + 1;
        int sum = 0;
        while (a <= mid && b <= R) {
            sum += arr[a] < arr[b] ? (R - b + 1) * arr[a] : 0;
            help[i++] = arr[a] < arr[b] ? arr[a++] : arr[b++];
        }
        while (a <= mid) {
            help[i++] = arr[a++];
        }
        while (b <= R) {
            help[i++] = arr[b++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        System.out.println(mergeSort(arr));
    }
}
