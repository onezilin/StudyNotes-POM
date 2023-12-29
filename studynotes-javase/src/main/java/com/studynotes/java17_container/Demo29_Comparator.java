package com.studynotes.java17_container;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: 测试比较器Comparator
 */
public class Demo29_Comparator {

    public static void main(String[] args) {
        Area a1 = new Area("1");
        Area a2 = new Area("1");
        Area a3 = new Area("1.1");
        Area a4 = new Area("1.1");
        Area a5 = new Area("1.1.1");

        Area[] array = new Area[]{a5, a4, a3, a2, a1};

        List<Area> list = Stream.of(array).collect(Collectors.toList());

        list.sort((o1, o2) -> -o1.getNbbm().compareTo(o2.getNbbm()));

        list.stream().map(Area::getNbbm)
                .sorted((o1, o2) -> -o1.compareTo(o2))
                .forEach(System.out::println);
    }

    @Data
    @AllArgsConstructor
    static class Area {
        String nbbm;
    }
}
