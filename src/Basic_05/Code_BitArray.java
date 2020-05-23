package Basic_05;

/**
 * Bloom Filter布隆过滤器中用到的bit数组实现举例
 */
public class Code_BitArray {

    /**
     * 0~32000 bit类型的数组实现
     */
    public static void main(String[] args) {

        int[] arr = new int[1000];  //32000
        long[] longArr = new long[1000];  //64000
        long[][] map = new long[1000][1000]; //64000000

        int index = 30000; //将30000的位置描黑

        int intIndex = index / 32;
        int bitIndex = index % 32;

        System.out.println(intIndex);   //第937个桶中
        System.out.println(bitIndex);   //第16个位置

        arr[intIndex] = (arr[intIndex] | (1 << bitIndex));  //或 进行描黑操作
    }
}
