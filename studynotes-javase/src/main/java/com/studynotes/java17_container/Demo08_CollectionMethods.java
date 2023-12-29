package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Description: 集合的基本方法及使用，Collections工具类的使用
 */
@Slf4j
public class Demo08_CollectionMethods {
    @Test
    public void allMethods() {
        Random rand = new Random(47);

        List<Fruit.Apple> appleList =
                IntStream.range(0, 10).mapToObj(i -> new Fruit.Apple()).collect(Collectors.toList());
        System.out.println("1: " + appleList);

        Fruit.Gala gala = new Fruit.Gala();
        appleList.add(gala);
        System.out.println("2: " + appleList);

        System.out.println("3: " + appleList.contains(gala));

        // Remove by object
        appleList.remove(gala);

        Fruit.Apple apple = appleList.get(2);
        System.out.println(
                "4: " + apple + " " + appleList.indexOf(apple));

        System.out.println("5: " + appleList.remove(apple));

        System.out.println("6: " + appleList);

        Fruit.Apple cymric = new Fruit.Apple();
        System.out.println("7: " + appleList.indexOf(cymric));

        System.out.println("8: " + appleList.remove(cymric));

        // 插入到指定位置
        appleList.add(3, new Fruit.Apple());
        System.out.println("9: " + appleList);

        List<Fruit.Apple> subList = appleList.subList(1, 4);
        System.out.println("subList: " + subList);

        System.out.println("10: " + appleList.containsAll(subList));

        //        Collections.sort(subList); // In-place sort
        //        System.out.println("sorted subList: " + sub);

        System.out.println("11: " + appleList.containsAll(subList));

        // 将集合中的元素顺序打乱
        Collections.shuffle(subList, rand);
        System.out.println("shuffled subList: " + subList);

        System.out.println("12: " + appleList.containsAll(subList));

        List<Fruit.Apple> copy = new ArrayList<>(appleList);
        subList = Arrays.asList(appleList.get(1), appleList.get(4));
        System.out.println("sub: " + subList);

        // 仅保留copy中在subList的元素
        copy.retainAll(subList);
        System.out.println("13: " + copy);

        copy = new ArrayList<>(appleList);
        copy.remove(2);
        System.out.println("14: " + copy);

        // 仅删除copy中在subList的元素
        copy.removeAll(subList);
        System.out.println("15: " + copy);

        // 替换指定位置元素
        copy.set(1, new Fruit.Fuji());
        System.out.println("16: " + copy);

        // 将集合全部插入到指定位置
        copy.addAll(2, subList);
        System.out.println("17: " + copy);

        System.out.println("18: " + appleList.isEmpty());

        // 删除所有元素
        appleList.clear();
        System.out.println("19: " + appleList);

        System.out.println("20: " + appleList.isEmpty());

        Object[] o = appleList.toArray();
        System.out.println("21: " + o[3]);

        Fruit.Apple[] pa = appleList.toArray(new Fruit.Apple[0]);
        System.out.println("22: " + pa[3].getId());
    }
}

