package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo10_Iterator {
    private void display(Iterator<Fruit.Apple> it) {
        while (it.hasNext()) {
            Fruit.Apple p = it.next();
            System.out.print(p.getId() + ":" + p + " ");
        }
        System.out.println();
    }

    @Test
    public void iteratorTest() {
        List<Fruit.Apple> appleList =
                IntStream.range(0, 10).mapToObj(i -> new Fruit.Apple()).collect(Collectors.toList());

        LinkedList<Fruit.Apple> petsLL = new LinkedList<>(appleList);
        HashSet<Fruit.Apple> petsHS = new HashSet<>(appleList);
        TreeSet<Fruit.Apple> petsTS = new TreeSet<>(appleList);

        display(appleList.iterator());
        display(petsLL.iterator());
        display(petsHS.iterator());
        display(petsTS.iterator());
    }
}

