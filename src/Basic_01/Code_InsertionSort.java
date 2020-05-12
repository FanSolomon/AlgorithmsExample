package Basic_01;

/**
 * 插入排序
 * 时间复杂度与数据状况有关
 * 最好时间复杂度 O(N)
 * 最坏时间复杂度O(N^2)
 *
 * 与数据状况有关时，一律按最差来估计
 */
public class Code_InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
    }
}
