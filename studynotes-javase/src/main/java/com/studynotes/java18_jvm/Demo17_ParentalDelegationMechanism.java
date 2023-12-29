package com.studynotes.java18_jvm;

// 双亲委派机制
public class Demo17_ParentalDelegationMechanism extends ClassLoader {
    public static void main(String[] args) {
        Demo17_ParentalDelegationMechanism demo10 = new Demo17_ParentalDelegationMechanism();
        System.out.println(demo10.getParent());
        System.out.println(demo10.getParent().getParent());
        System.out.println(demo10.getParent().getParent().getParent());
    }
}
