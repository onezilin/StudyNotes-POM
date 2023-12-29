package com.studynotes.java18_jvm;

// 静态分派中，重载方法的理解
public class Demo02_StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy！ ");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman！ ");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady！ ");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        Demo02_StaticDispatch sr = new Demo02_StaticDispatch();
        // 方法重载时，只看静态类型Man，不看实际类型
        sr.sayHello(man);
        sr.sayHello(woman);
        sr.sayHello((Man) man);
        sr.sayHello((Woman) woman);
    }
}
