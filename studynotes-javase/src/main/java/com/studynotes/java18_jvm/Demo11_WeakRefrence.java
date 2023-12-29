package com.studynotes.java18_jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// 弱引用：不管jvm内存是否足够，在下一次GC时，就会回收
public class Demo11_WeakRefrence {
    static List<WeakReference> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            byte[] buff = new byte[1024 * 1024];
            WeakReference<byte[]> sr = new WeakReference<>(buff);
            list.add(sr);
        }

        //        System.gc(); //主动通知垃圾回收

        for (int i = 0; i < list.size(); i++) {
            Object obj = (list.get(i)).get();
            System.out.println(obj);
        }
    }
}
