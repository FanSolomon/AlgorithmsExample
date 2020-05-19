package Basic_04;

import java.util.Stack;

/**
 * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式
 */
public class Code_PreInPosTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderRecur(root.left);
        preOrderRecur(root.right);
    }

    public static void inOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        inOrderRecur(root.left);
        System.out.print(root.value + " ");
        inOrderRecur(root.right);
    }

    public static void posOrderRecur(Node root) {
        if (root == null) {
            return;
        }
        posOrderRecur(root.left);
        posOrderRecur(root.right);
        System.out.print(root.value + " ");
    }

    public static void preOrderUnRecur(Node root) {
        System.out.print("pre-order: ");
        if (root == null) {
            System.out.println();
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node root) {
        System.out.print("in-order: ");
        if (root == null) {
            System.out.println();
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node cur = root.left;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历为左右根顺序，通过自己压栈不易实现，
     * 需要返回root三次 前两种都只要返回两次
     * 可以先记录根右左顺序，然后进行逆序 得到根左右后序遍历顺序
     */
    public static void posOrderUnRecur(Node root) {
        System.out.print("pos-order: ");
        if (root == null) {
            System.out.println();
            return;
        }
        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        Node cur;
        stack.push(root);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            sb.insert(0, cur.value + " ");
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
//        posOrderUnRecur2(head);

    }

}
