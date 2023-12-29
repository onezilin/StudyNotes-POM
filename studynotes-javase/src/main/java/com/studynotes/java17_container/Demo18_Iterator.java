package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Description: Collection和iterator比较使用
 */
public class Demo18_Iterator {
    public static void display(Iterator<Fruit.Apple> it) {
        while (it.hasNext()) {
            Fruit.Apple p = it.next();
            System.out.print(p.getId() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Fruit.Apple> pets) {
        for (Fruit.Apple p : pets)
            System.out.print(p.getId() + ":" + p + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Fruit.Apple> petList =
                IntStream.range(0, 10).mapToObj(i -> new Fruit.Apple()).collect(Collectors.toList());

        Set<Fruit.Apple> petSet = new HashSet<>(petList);

        Map<String, Fruit.Apple> petMap = new LinkedHashMap<>();
        String[] names = ("Ralph, Eric, Robin, Lacey, " +
                "Britney, Sam, Spot, Fluffy").split(", ");
        for (int i = 0; i < names.length; i++)
            petMap.put(names[i], petList.get(i));

        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());
    }
}



