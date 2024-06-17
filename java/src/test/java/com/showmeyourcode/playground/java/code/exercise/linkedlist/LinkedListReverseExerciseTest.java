package com.showmeyourcode.playground.java.code.exercise.linkedlist;

import org.junit.jupiter.api.Test;

import static com.showmeyourcode.playground.java.code.exercise.linkedlist.LinkedListReverseExercise.constructLinkedList;
import static com.showmeyourcode.playground.java.code.exercise.linkedlist.LinkedListReverseExercise.reverseListIterative;
import static com.showmeyourcode.playground.java.code.exercise.linkedlist.LinkedListReverseExercise.reverseListRecursive;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LinkedListReverseExerciseTest {

    @Test
    void shouldRevereLinkedListsUsingIterativeAlgorithm() {
        LinkedListReverseExercise.ListNode head = constructLinkedList();
        LinkedListReverseExercise.ListNode node = head;
        for (int i = 1; i <= 5; i++) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }

        node = reverseListIterative(head);

        for (int i = 5; i >= 1; i--) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
    }

    @Test
    void shouldRevereLinkedListsUsingRecursiveAlgorithm() {
        LinkedListReverseExercise.ListNode head = constructLinkedList();
        LinkedListReverseExercise.ListNode node = head;
        for (int i = 1; i <= 5; i++) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }

        node = reverseListRecursive(head);

        for (int i = 5; i >= 1; i--) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
    }
}
