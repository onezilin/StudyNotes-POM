package com.studynotes.java18_jvm;

// 动态分派的多分派和单分派
public class Demo05_DynamicDispatch {
    static class QQ {
    }

    static class _360 {
    }

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        // 重载时是多分派的、重写时是单分派的
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}
