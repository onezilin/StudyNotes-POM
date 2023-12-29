package com.studynotes.java07_generic;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Description: getTypeParameters 返回类上声明的泛型名数组，例如：T V K 这种作为占位符的标志符
 */
public class Demo05_GetTypeParameters {

    @Test
    void test() {
        List<Frob> list = new ArrayList<>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters())); // [E]

        Map<Frob, Fnorkle> map = new HashMap<>();
        System.out.println(Arrays.toString(map.getClass().getTypeParameters())); // [K, V]

        Quark<Fnorkle> quark = new Quark<>();
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters())); // [Q]

        Particle<Long, Double> p = new Particle<>();
        System.out.println(Arrays.toString(p.getClass().getTypeParameters())); // [POSITION, MOMENTUM]
    }


    static class Frob {
    }

    static class Fnorkle {
    }

    static class Quark<Q> {
    }

    static class Particle<POSITION, MOMENTUM> {
    }
}
