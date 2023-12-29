package com.studynotes.java18_jvm;

// 探讨Super关键字的含义，查看Super真正的实例指向
public class Demo18_Super {

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.toString());
    }
}

class Father {
    Father() {
        System.out.println(this.toString());
    }
}

class Son extends Father {
    Son() {
        super();
        System.out.println(this.toString());
    }
}
