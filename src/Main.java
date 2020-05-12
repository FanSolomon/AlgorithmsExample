import Basic_01.Code_BubbleSort;
import Basic_01.Code_InsertionSort;
import Basic_01.Code_MergeSort;
import Basic_01.Code_SelectionSort;

import java.util.Arrays;

/**
 * 对数器
 *
 * 1.足够大的测试数据量
 * 2.绝对正确的验证方法
 */
public class Main {

    // for test
    public static void main(String[] args) {
        int testTime = 500000;  //测试次数 50万
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

//            Code_InsertionSort.insertionSort(arr1);
//            Code_SelectionSort.selectionSort(arr1);
//            Code_BubbleSort.bubbleSort(arr1);
            Code_MergeSort.mergeSort(arr1);

            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);

        Code_InsertionSort.insertionSort(arr);
        Code_SelectionSort.selectionSort(arr);
        Code_BubbleSort.bubbleSort(arr);

        printArray(arr);
    }

//    public static void main(String[] args) {
//        int[] arr = {6,3,8,9,0,10,2};
//
////        Code_BubbleSort.bubbleSort(arr);
////        Code_SelectionSort.selectionSort(arr);
//        Code_InsertionSort.insertionSort(arr);
//
//        System.out.println(Arrays.toString(arr));
//
//
//    }

    /**
     * 绝对正确的方法
     * 也可以自己写，复杂度可能会高，但可以保证绝对正确
     *
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 生成长度随机的数组
     *
     * @param maxSize  数组最大长度
     * @param maxValue 数组的值 -maxValue~maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * copy a new arr
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
