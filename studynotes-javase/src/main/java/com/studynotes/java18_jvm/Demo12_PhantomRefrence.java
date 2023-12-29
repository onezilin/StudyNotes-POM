package com.studynotes.java18_jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo12_PhantomRefrence {
    public static void main(String[] args) {
        List<byte[]> bytes = new ArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bytes.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Student> reference =
                new PhantomReference<Student>(new Demo12_PhantomRefrence().new Student(), queue);
        new Thread(() -> {
            while (true) {
                Reference poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
    }

    class Student {
        // Java Object finalize() 方法用于实例被垃圾回收器回收的时触发的操作
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Student 被回收了");
        }
    }
}
