package com.showmeyourcode.playground.java.code.exercise.linkedlist;

import lombok.Getter;
import lombok.Setter;

/**
 * See: <a href="https://www.baeldung.com/java-reverse-linked-list">Java reverse linked list</a>
 */
class LinkedListReverseExercise {

    private LinkedListReverseExercise() {
    }

    @Getter
    @Setter
    static class ListNode {

        private int data;
        private ListNode next;

        ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static ListNode constructLinkedList() {
        ListNode head = null;
        ListNode tail = null;
        for (int i = 1; i <= 5; i++) {
            ListNode node = new ListNode(i);
            if (head == null) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
        }
        return head;
    }

    static ListNode reverseListIterative(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextElement = current.getNext();
            current.setNext(previous);
            previous = current;
            current = nextElement;
        }
        return previous;
    }

    static ListNode reverseListRecursive(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        ListNode node = reverseListRecursive(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return node;
    }
}
