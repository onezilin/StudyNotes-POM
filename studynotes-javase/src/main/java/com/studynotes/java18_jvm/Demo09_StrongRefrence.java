package com.studynotes.java18_jvm;

// 强引用：若引用指着对象，对象永远不会被回收
public class Demo09_StrongRefrence {
    public static void main(String[] args) {
        Object o = new Object();
        // 引用值为null，new Object()对象可被回收
        o = null;
    }
}
