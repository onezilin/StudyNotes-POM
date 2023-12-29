package com.studynotes.java17_container;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Description: Stack和ArrayDeque实现的Stack比较
 */
public class Demo14_Stack {
    @Test
    public void stackTest() {
        Stack<String> stack = new Stack<>();
        for (String s : "My dog has fleas".split(" ")) stack.push(s);
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");

        Stack<String> stack2 = new Stack<>();
        for (String s : "My dog has fleas".split(" ")) stack2.push(s);
        while (!stack2.empty()) System.out.print(stack2.pop() + " ");
    }
}
