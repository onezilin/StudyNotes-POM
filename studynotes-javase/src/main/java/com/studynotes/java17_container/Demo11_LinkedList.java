package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Description: LinkedList实现Deque接口，双向链表
 */
public class Demo11_LinkedList {
    public static void main(String[] args) {
        LinkedList<Fruit.Apple> appleLinkedList = new LinkedList<>();
        Collections.addAll(appleLinkedList, new Fruit.Apple(), new Fruit.Apple(), new Fruit.Apple(),
                new Fruit.Apple(), new Fruit.Apple(), new Fruit.Apple());

        System.out.println(appleLinkedList);

        // Identical:
        System.out.println(
                "appleLinkedList.getFirst(): " + appleLinkedList.getFirst());

        System.out.println(
                "appleLinkedList.element(): " + appleLinkedList.element());

        // Only differs in empty-list behavior:
        System.out.println("appleLinkedList.peek(): " + appleLinkedList.peek());

        // Identical; remove and return the first element:
        System.out.println(
                "appleLinkedList.remove(): " + appleLinkedList.remove());

        System.out.println(
                "appleLinkedList.removeFirst(): " + appleLinkedList.removeFirst());

        // Only differs in empty-list behavior:
        System.out.println("appleLinkedList.poll(): " + appleLinkedList.poll());

        System.out.println(appleLinkedList);

        appleLinkedList.addFirst(new Fruit.Apple());
        System.out.println("After addFirst(): " + appleLinkedList);

        appleLinkedList.offer(new Fruit.Apple());
        System.out.println("After offer(): " + appleLinkedList);

        appleLinkedList.add(new Fruit.Apple());
        System.out.println("After add(): " + appleLinkedList);

        appleLinkedList.addLast(new Fruit.Gala());
        System.out.println("After addLast(): " + appleLinkedList);

        System.out.println(
                "appleLinkedList.removeLast(): " + appleLinkedList.removeLast());
    }
}
