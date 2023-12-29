package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Description: 使用Deque实现Stack
 */
public class Demo13_Deque {

    class Stack<T> {
        private Deque<T> storage = new ArrayDeque<>();

        public void push(T v) {
            storage.push(v);
        }

        public T peek() {
            return storage.peek();
        }

        public T pop() {
            return storage.pop();
        }

        public boolean isEmpty() {
            return storage.isEmpty();
        }

        @Override
        public String toString() {
            return storage.toString();
        }
    }

    @Test
    public void stackTest() {
        Stack<String> stack = new Stack<>();
        for (String s : "My dog has fleas".split(" ")) stack.push(s);
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }
}

