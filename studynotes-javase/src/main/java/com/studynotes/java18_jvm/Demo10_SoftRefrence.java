package com.studynotes.java18_jvm;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

// 软引用
public class Demo10_SoftRefrence {
    static List<SoftReference> list = new ArrayList<>();

    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[1024 * 1024 * 10]);

        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println(softReference.get());
    }
}

