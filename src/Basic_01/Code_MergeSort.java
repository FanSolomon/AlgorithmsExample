package Basic_01;

/**
 * 归并排序
 * 时间复杂度：O(N*logN)
 */
public class Code_MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1); //等同于(L + R)/2;
        mergeSort(arr, L, mid);   // T(N/2)
        mergeSort(arr, mid+1, R);   // T(N/2)
        merge(arr, L, mid, R);  // O(N)
        //T(N) = 2 T(N/2) + O(N)
    }

    public static void merge(int[] arr, int L, int mid, int R) {
        int a = L;
        int b = mid+1;
        int[] help = new int[R - L + 1];
        int i = 0;

        while (a <= mid && b <= R) {
            help[i++] = arr[a] < arr[b] ? arr[a++] : arr[b++];
        }
        //两个有且必有一个越界
        while (a <= mid) {
            help[i++] = arr[a++];
        }
        while (b <= R) {
            help[i++] = arr[b++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
    }
}
