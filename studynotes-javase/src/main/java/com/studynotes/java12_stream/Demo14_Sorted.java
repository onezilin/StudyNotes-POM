package com.studynotes.java12_stream;

import lombok.Data;

import java.util.stream.Stream;

/**
 * Description:
 * 无参的 sorted() 传入 Comparable 接口的实现类，进行排序
 * 有参的 sorted() 可以传入一个外部比较器 Comparator 进行排序
 */
public class Demo14_Sorted {
    public static void main(String[] args) {
        Demo01 demo01 = new Demo01("Alice", 20);
        Demo01 demo02 = new Demo01("Alice", 21);
        Demo01 demo03 = new Demo01("Bob", 20);
        Demo01 demo04 = new Demo01("Candy", 20);
        Demo01 demo05 = new Demo01("Dick", 20);
        Demo01 demo06 = new Demo01("Ella", 20);
        Demo01 demo07 = new Demo01("Dick", 19);

        Demo01[] demoArray = {demo01, demo02, demo03, demo04, demo05, demo06, demo07};
        Stream.of(demoArray)
                .peek(demo -> System.out.println(demo.toString()))
                // 根据名字从小到大排序，名字相同根据年龄从小到大
                .sorted((a, b) -> {
                    int nameOrder = a.getName().compareTo(b.getName());
                    if (nameOrder < 0) {
                        return -1;
                    } else if (nameOrder == 0) {
                        return Integer.compare(a.getAge(), b.getAge());
                    } else {
                        return 1;
                    }
                })
                .forEach(demo -> System.out.println(demo.toString()));
    }

    @Data
    static class Demo01 {
        private String name;
        private int age;

        Demo01(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "[" + name + ":" + age + "]";
        }
    }
}

