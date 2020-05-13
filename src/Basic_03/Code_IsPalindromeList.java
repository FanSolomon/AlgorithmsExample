package Basic_03;

import java.util.Stack;

/**
 * 判断一个链表是否为回文结构
 * 给定一个链表的头节点head，请判断该链表是否为回 文结构。
 * 例如： 1->2->1，返回true。 1->2->2->1，返回true。
 * 15->6->15，返回true。 1->2->3，返回false。
 *
 * 进阶： 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂 度达到O(1)。
 */
public class Code_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    // need n extra space
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        Node slower = head;
        Node faster = head;
        while (faster != null) {
            slower = slower.next;
            faster = faster.next == null ? null : faster.next.next;
        }
        while (slower != null) {
            stack.push(slower);
            slower = slower.next;
        }
        while (!stack.isEmpty() && cur != slower) {
            if (stack.pop().value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head) {
        boolean isPalindrome = true;
        if (head == null || head.next == null) {
            return true;
        }
        Node slower = head;
        Node faster = head;
        Node tmp;
        Node tmpPre;
        Node tmpNext;
        Node end;
        Node slowerPre = head;
        while (faster != null) {
            slowerPre = slower;
            slower = slower.next;
            if (faster.next == null) {
                slowerPre = slower;
            }
            faster = faster.next == null ? null : faster.next.next;
        }
        tmp = slower.next;
        tmpPre = slower;
        slower.next = null;
        while (tmp != null) {
            tmpNext = tmp.next;
            tmp.next =tmpPre;
            tmpPre = tmp;
            tmp = tmpNext;
        }
        end = tmpPre;
        while (tmpPre != null && head != null) {
            if (tmpPre.value != head.value) {
                isPalindrome = false;
            }
            tmpPre = tmpPre.next;
            head = head.next;
        }
        tmp = end.next;
        tmpPre = end;
        while (tmp != slowerPre && tmp != null && tmp.next != null) {
            tmpNext = tmp.next;
            tmp.next = tmpPre;
            tmpPre = tmp;
            tmp = tmpNext;
        }
        return isPalindrome;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
