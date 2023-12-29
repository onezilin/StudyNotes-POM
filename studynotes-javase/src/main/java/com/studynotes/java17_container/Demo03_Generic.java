package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Description: 泛型的向上转型
 */
public class Demo03_Generic {

    @Test
    public void upperGeneric() {
        ArrayList<Fruit.Apple> apples = new ArrayList<>();
        apples.add(new Fruit.GrannySmith());
        apples.add(new Fruit.Gala());
        apples.add(new Fruit.Fuji());
        apples.add(new Fruit.Braeburn());
        for (Fruit.Apple apple : apples)
            System.out.println(apple);
    }
}

