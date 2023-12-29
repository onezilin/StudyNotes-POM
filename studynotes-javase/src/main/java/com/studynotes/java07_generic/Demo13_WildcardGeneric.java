package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Description: 上界通配符（无界通配符就相当于 `<? extends Object>`）
 */
public class Demo13_WildcardGeneric {

    @Test
    void foreach(List<? extends Father> list) {
        for (Object o : list) {
            System.out.println(o);
        }
        list.add(null);
        // list.add(new Father()); // 编译报错
        // list.add(new Son()); // 编译报错
        // list.add(new Daughter()); // 编译报错
    }

    static class Father {
    }

    static class Son extends Father {
    }

    static class Daughter extends Father {
    }
}
