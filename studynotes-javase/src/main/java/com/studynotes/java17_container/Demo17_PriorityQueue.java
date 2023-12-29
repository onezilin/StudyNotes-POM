package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Description:  优先级队列，可以为队列指定优先级规则，底层实现还是实现Comparator接口，每次插入一个元素都会进行排序
 */
public class Demo17_PriorityQueue {
    @Test
    public void priorityQueueTest(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) priorityQueue.offer(rand.nextInt(i + 10));
        Demo16_LinkedList.printQ(priorityQueue);

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        Demo16_LinkedList.printQ(priorityQueue);

        priorityQueue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        Demo16_LinkedList.printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<>(strings);
        Demo16_LinkedList.printQ(stringPQ);

        stringPQ = new PriorityQueue<>(strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        Demo16_LinkedList.printQ(stringPQ);

        Set<Character> charSet = new HashSet<>();
        for (char c : fact.toCharArray()) charSet.add(c); // Autoboxing
        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);
        Demo16_LinkedList.printQ(characterPQ);
    }
}

