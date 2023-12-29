package com.studynotes.java07_generic;

import java.util.function.Supplier;

/**
 * Description: 使用工厂模式创建泛型的对象，解决泛型擦除问题
 */
public class Demo09_SolveErasure {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println(fe.get());

        ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        System.out.println(fi.get());
    }

    static class ClassAsFactory<T> implements Supplier<T> {
        Class<T> kind;

        ClassAsFactory(Class<T> kind) {
            this.kind = kind;
        }

        @Override
        public T get() {
            try {
                // 调用Class<T>的newInstance()
                return kind.newInstance();
            } catch (InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Employee {
        @Override
        public String toString() {
            return "Employee";
        }
    }
}

