package com.studynotes.java17_container;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Description:  比较器接口Comparable测试内部排序
 */
public class Demo25_Comparable {
    public static void main(String[] args) {
        Person p0 = new Person("张三", 3);
        Person p2 = new Person("张四", 2);
        Person p = new Person("张三", 1);
        Person p1 = new Person("张三", 2);
        Person p3 = new Person("张四", 2);

        TreeSet<Person> treeSet = new TreeSet<Person>();
        Collections.addAll(treeSet, p0, p, p1, p2, p3);

        Iterator<Person> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            System.out.println(next);
        }
    }


    @Data
    @AllArgsConstructor
    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        @Override
        public int compareTo(Person o) {
            if (this.age < o.age) {
                return -1;
            } else if (this.age == o.age) {
                return this.name.compareTo(o.name);
            } else {
                return 1;
            }
        }
    }
}
