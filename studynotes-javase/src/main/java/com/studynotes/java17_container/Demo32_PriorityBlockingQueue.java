package com.studynotes.java17_container;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Description: debug PriorityBlockingQueue
 */
public class Demo32_PriorityBlockingQueue {

    public static void main(String[] args) {
        Random rand = new Random(47);
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        rand.ints(10, 0, 100).forEach(priorityBlockingQueue::add);
        while (!priorityBlockingQueue.isEmpty()) {
            System.out.println(priorityBlockingQueue.poll());
        }
    }
}
