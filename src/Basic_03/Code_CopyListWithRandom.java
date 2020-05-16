package Basic_03;

import java.util.HashMap;

/**
 * 复制含有随机指针节点的链表
 *
 * 一种特殊的链表节点类描述如下：
 * public class Node { public int value; public Node next; public Node rand;
 * public Node(int data) { this.value = data; }}
 * Node类中的value是节点值， next指针和正常单链表中next指针的意义
 * 一 样， 都指向下一个节点， rand指针是Node类中新增的指针， 这个指
 * 针可 能指向链表中的任意一个节点， 也可能指向null。 给定一个由
 * Node节点类型组成的无环单链表的头节点head， 请实现一个 函数完成
 * 这个链表中所有结构的复制， 并返回复制的新链表的头节点。 进阶：
 * 不使用额外的数据结构， 只用有限几个变量， 且在时间复杂度为 O(N)
 * 内完成原问题要实现的函数。
 *
 * p.s.复制链表用的空间不算做额外空间
 */
public class Code_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;
        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 利用HashMap求解
     */
    public static Node copyListWithRand1(Node head) {
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = cur.next;
            map.get(cur).rand = cur.rand;
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 通过将原链表中插入复制的链表结点实现
     * 如原 1->2->3 到 1->1'->2->2'->3->3'
     * 来实现原结点寻找其复制结点需求
     */
    public static Node copyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node curNext;
        while (cur != null) {
            curNext = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = curNext;
            cur = curNext;
        }
        cur = head;
        Node curNextNext;
        Node head1 = cur.next;
        while (cur != null) {
            cur.next.rand = cur.rand;
            curNextNext = cur.next.next;
            curNext = cur.next;
            cur.next = curNextNext;
            cur = curNextNext;
            curNext.next = curNextNext == null ? null : curNextNext.next;
        }
        return head1;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
