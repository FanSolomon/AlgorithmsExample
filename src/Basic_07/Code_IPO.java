package Basic_07;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：参数1，正数数组costs 参数2，正数数组profits 参数3，正数k 参数4，正数m
 * costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你不能并行、 只能串行的最多做k个项目 m表示你初始的资金
 * 说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。项目不能够重复做，
 *      做完就没了。
 * 输出：你最后获得的最大钱数。
 *
 * 贪心策略，利用堆
 * 首先将所有输入按照costs大小排出一个小根堆，再将小根堆中小于m（已有资金）的参数弹出，
 * 将所有取出的参数根据profits值放入一个大根堆中，则每次只需要取大根堆的堆顶，即当前
 * 选择去做的项目，已有资金增加后，再从小根堆中弹出相应项目放入大根堆中。每次做完一个项目
 * 需要将大根堆中的该项目去除，弹出即可。
 */
public class Code_IPO {

    public static class Node {
        public int c;
        public int p;

        public Node(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int W, int[] profits, int[] capital) {
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i] = new Node(capital[i], profits[i]);
        }

        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        Collections.addAll(minCostQ, nodes);
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c < W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }
}
