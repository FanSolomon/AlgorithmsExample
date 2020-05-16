package Basic_03;

import java.util.HashSet;

/**
 * 两个单链表相交的一系列问题
 *
 * 在本题中，单链表可能有环，也可能无环。给定两个单链表的头节点head1和head2，
 * 这两个链表可能相交，也可能不相交。 请实现一个函数， 如果两个链表相交，
 * 请返回相交的第一个节点； 如果不相交， 返回null 即可。
 * 要求： 如果链表1的长度为N， 链表2的长度为M， 时间复杂度请达到 O(N+M)，
 * 额外空间复杂度请达到O(1)。
 *
 * 先判断两链表是否有环，再分情况讨论是否相交
 */
public class Code_FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 利用HashSet判断是否有环 返回环的起始点
     */
    public static Node getLoopNode1(Node head) {
        HashSet<Node> set = new HashSet<>();
        while (head != null && !set.contains(head)) {
            set.add(head);
            head = head.next;
        }
        return head;
    }

    /**
     * 快指针一次走两步 慢指针一次走一步
     * 若快指针和满指针相遇 则表示有环
     * 此时，快指针回到head，并改为每次走一步
     * 当快指针和慢指针相遇时，改点就是环的起始点
     */
    public static Node getLoopNode2(Node head) {
        Node faster = head;
        Node slower = head;
        while (faster != null) {
            faster = faster.next == null ? null : faster.next.next;
            slower = slower.next;
            if (faster == slower) {
                break;
            }
        }
        faster = faster == null ? null : head;
        while (faster != null && faster != slower) {
            faster = faster.next;
            slower = slower.next;
        }
        return faster;
    }

    /**
     * 两个链表都无环时
     */
    public static Node getIntersectNodeNoLoop(Node head1, Node head2, Node end) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node end1 = head1;
        Node end2 = head2;
        int length1 = 1;
        int length2 = 1;
        while (end1.next != end) {
            end1 = end1.next;
            length1++;
        }
        while (end2.next != end) {
            end2 = end2.next;
            length2++;
        }
        if (end1 != end2) {
            return null;
        }
        int subtract = length1 > length2 ? length1 - length2 : length2 - length1;
        if (length1 > length2) {
            while (subtract > 0) {
                head1 = head1.next;
                subtract--;
            }
        } else {
            while (subtract > 0) {
                head2 = head2.next;
                subtract--;
            }
        }
        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode2(head1);
        Node loop2 = getLoopNode2(head2);
        if (loop1 != loop2 && (loop1 == null || loop2 == null)) {
            //两个中 一个有环 一个无环 则不存在交点
            return null;
        } else if (loop1 != null) {
            //两个都有环时 分三种情况
            if (loop1 == loop2) {
                //两个环起始点一样时 交点就是环的起始点在向上找
                return getIntersectNodeNoLoop(head1, head2, loop1);
            } else {
                //这时 可能相交 也可能不相交
                Node cur = loop1.next;
                while (cur != loop1) {
                    if (cur == loop2) {
                        return cur;
                    }
                    cur = cur.next;
                }
                return null;
            }
        } else {
            //两个都无环时
            return getIntersectNodeNoLoop(head1, head2, null);
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}
