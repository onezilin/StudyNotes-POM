package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Description: 泛型可以将运行时期异常提前到编译时期，下面是不使用泛型产生的错误
 */
public class Demo01_Generic {

    @Test
    public void withoutGeneric() {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++) apples.add(new Fruit.Apple());
        // No problem adding an Orange to apples:
        apples.add(new Fruit.Orange());
        for (Object apple : apples) {
            ((Fruit.Apple) apple).getId();
        }
    }
}

