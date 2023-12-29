package com.studynotes.java18_jvm;

public class Demo15_TriggerGC {
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * 1024 * 1024];
        allocation2 = new byte[2 * 1024 * 1024];
        allocation3 = new byte[2 * 1024 * 1024];
        allocation4 = new byte[2 * 1024 * 1024]; // 出现一次Minor GC
    }
}
