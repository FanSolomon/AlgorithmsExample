package Basic_08;

/**
 * 打印一个字符串的全部子序列，包括空字符串
 *
 * 如"abc"有"abc" "ac" "bc" "a" "b" "c" ""
 * 设想abc的位置分别是012 那么问题可以等同于每个位置有和没有该字符
 * 然后利用递归穷举出所有可能
 */
public class Code_Print_All_Subsquences {

    /**
     * @param str 需要打印的字符串
     * @param i 当前需要决策的位置
     * @param res 上一级扔过来的之前位置决策后组成的字符串
     */
    public static void printAllSub(char[] str, int i, String res) {
        if (i >= str.length) {
            System.out.println(res);
            return;
        }
        printAllSub(str, i + 1, res+str[i]);
        printAllSub(str, i + 1, res+" ");
    }

    public static void main(String[] args) {
        char[] str = {'a','b','c'};
        printAllSub(str, 0, "");
    }
}
