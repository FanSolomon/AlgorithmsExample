package Basic_04;

/**
 * 已知一棵完全二叉树， 求其节点的个数
 * 要求： 时间复杂度低于O(N)， N为这棵树的节点个数
 *
 * 先看树的左边界 判断树高度h
 * 然后看右子树的左边界：
 *      1.右子树的左边界到最后一层，则左子树是满的，2^(h-1) - 1 + 1。再递归计算右子树
 *      2.没到最后一层，则右子树是满的, 2^(h-2) -  1 + 1。再递归计算左子树
 *
 * 时间复杂度：O((logN) ^ 2)
 */
public class Code_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getNum(Node root) {
        if (root == null) {
            return 0;
        }
        return bs(root, mostLeftLevel(root, 1));
    }

    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static int bs(Node root, int height) {
        if (root == null) {
            return 0;
        }
        int rightH = mostLeftLevel(root.right, 2);
        if (height == rightH) {
            return (1 << (height - 1)) + bs(root.right, height - 1);
        } else {
            return (1 << (height - 2)) + bs(root.left, height - 1);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(getNum(head));

    }
}
