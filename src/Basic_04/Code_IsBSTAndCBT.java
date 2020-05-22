package Basic_04;

import java.util.*;

/**
 * 判断一棵树是否是搜索二叉树、 判断一棵树是否是完全二叉树
 *
 * BST Binary Search Tree搜索二叉树：对于树上任何一个节点，左子树都比它小，右子树都比它大
 *              ，通常不含有重复节点
 * 判断：中序遍历的结果是依次升序的
 *
 * CBT Complete Binary Tree完全二叉树
 * 先按层遍历
 *
 * 判断：1.有右孩子 没左孩子 返回false
 *      2.左右孩子不是双全（左有右无，都无） 后面遇到的结点必须都是叶结点
 *      不违反1、2，则是CBT
 */
public class Code_IsBSTAndCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST(Node root) {
        if (root == null) {
            System.out.println();
            return false;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur = root.left;
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.value < preValue) {
                    return false;
                }
                preValue = cur.value;
                cur = cur.right;
            }
        }
        return true;
    }

    public static boolean isCBT(Node root) {
        if (root == null) {
            System.out.println();
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node leftNode;
        Node rightNode;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            root = queue.poll();
            leftNode = root.left;
            rightNode = root.right;
            if ((leaf && (leftNode != null || rightNode != null)) || (leftNode == null && rightNode != null)) {
                return false;
            }
            if (leftNode != null) {
                queue.offer(leftNode);
            }
            if (rightNode != null) {
                queue.offer(rightNode);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
//        head.right.right = new Node(7);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));

    }
}
