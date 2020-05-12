package Basic_01;

import java.util.Arrays;

/**
 * 堆排序
 *
 * 将数组调整为大根堆
 */
public class Code_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i); //0~i位置排大顶堆
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 在当前大根堆中插入一个数的操作，维护大根堆
     * @param arr
     * @param i i是当前插入数的下标
     */
    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    /**
     * heapify过程。大根堆中i位置上的值发生变化后，继续维护一个大根堆
     * 一个值变小往下沉的操作
     * @param arr 数组
     * @param i 发生变化的值的下标
     * @param heapSize 维护大根堆的size
     */
    public static void heapify(int[] arr, int i, int heapSize) {
        int left = 2 * i + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i) {
                break;
            }
            swap(arr, i, largest);
            i = largest;
            left = 2 * i + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,5,2,7,1,2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        arr[0] = 0;
        heapify(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
