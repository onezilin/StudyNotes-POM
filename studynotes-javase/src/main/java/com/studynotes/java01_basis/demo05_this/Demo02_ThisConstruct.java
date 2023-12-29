package com.studynotes.java01_basis.demo05_this;

/**
 * Description: this() 调用本类指定参数的构造方法
 */
public class Demo02_ThisConstruct {

    class Person {

        Person() {
            // System.out.println("Person"); // this() 必须放在构造方法的第一行，前面不允许有任何代码
            this(2);
        }

        private Person(int i) {
            System.out.println("Person " + i);
        }
    }
}
