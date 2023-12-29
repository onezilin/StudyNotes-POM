package com.studynotes.java17_container;

// collections/StackTest.java

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Description: Deque是双向队列接口
 */
public class Demo12_Deque {
    @Test
    public void initDeque() {
        // ArrayDeque虽然没有继承Stack，但是已经全部实现Stack的功能
        Deque<String> s = new ArrayDeque<>();
    }

    @Test
    public void dequeTest() {
        Deque<String> stack = new ArrayDeque<>();
        for (String s : "My dog has fleas".split(" "))
            stack.push(s);
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }
}
