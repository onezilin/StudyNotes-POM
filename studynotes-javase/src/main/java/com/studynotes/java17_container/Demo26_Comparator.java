package com.studynotes.java17_container;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

// 比较器接口Comparator测试，外部比较
public class Demo26_Comparator {
    public static void main(String[] args) {
        Person p0 = new Person("张三", 3);
        Person p = new Person("张三", 1);
        Person p1 = new Person("张三", 2);
        Person p2 = new Person("张四", 2);
        Person p3 = new Person("张四", 2);

        TreeSet<Person> treeSet = new TreeSet<>(new Com());
        Collections.addAll(treeSet, p0, p, p1, p2, p3);

        Iterator<Person> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            System.out.println(next);
        }
    }

    static class Com implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            if (o1.age < o2.age) {
                return -1;
            } else if (o1.age == o2.age) {
                return o1.name.compareTo(o2.name);
            } else {
                return 1;
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Person {
        private String name;
        private int age;
    }
}
