package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Description: 使用泛型的情况
 */
public class Demo02_Generic {

    @Test
    public void withGeneric() {
        ArrayList<Fruit.Apple> apples = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            apples.add(new Fruit.Apple());
        // 编译不通过
        //		  apples.add(new Demo01_Generic().new Orange());
        for (Fruit.Apple apple : apples) {
            System.out.println(apple.getId());
        }
    }
}

