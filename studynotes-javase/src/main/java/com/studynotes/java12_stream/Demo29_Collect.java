package com.studynotes.java12_stream;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description: collect() 将流转换为其他形式，接受一个 Collector 接口的实现，用于给流中元素做汇总的方法
 */
public class Demo29_Collect {
    public static void main(String[] args) {
        Map<Integer, Character> map =
                new Demo28_RandomPair().stream()
                        .limit(8)
                        // 第 1 个参数是 key
                        // 第 2 个参数是 value
                        // 第 3 个参数是当 key 重复时，如何处理
                        .collect(Collectors.toMap(Demo28_RandomPair.Pair::getI, Demo28_RandomPair.Pair::getC));
        System.out.println(map);

        Instant instant1 = Instant.now();
        List<Demo28_RandomPair.Pair> pairList = new Demo28_RandomPair()
                .stream()
                .limit(8)
                // 注意这里：capChars是Iterator，多线程情况下可能会抛异常
                // .parallel()
                // 第 1 个参数是 Supplier，用于创建容器
                // 第 2 个参数是 BiConsumer，用于将流中元素放入容器
                // 第 3 个参数是 BiConsumer，用于将两个容器合并，parallel() 时才会用到
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(pairList.toString());

        List<Demo28_RandomPair.Pair> pairList1 = new Demo28_RandomPair().stream()
                .limit(1000)
                .collect(Collectors.toCollection(ArrayList::new))
                .parallelStream()
                //                .stream()
                .collect(ArrayList::new,
                        ArrayList::add,
                        (list1, list2) -> {
                            System.out.println(Integer.toHexString(list1.hashCode()));
                            System.out.println(Integer.toHexString(list2.hashCode()));
                            list1.addAll(list2);
                        });
        System.out.println("List长度" + pairList1.size());
        System.out.println("比较Parallel和非Parallel区别" + Duration.between(instant1, Instant.now()).toMillis());
    }


}
