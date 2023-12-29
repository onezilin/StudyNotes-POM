package com.studynotes.java12_stream;

import java.util.stream.Stream;

/**
 * Description: Stream.iterate() 用于生成一个无限流
 * <p>
 * limit() 限制流内部元素的个数
 * skip() 从头开始算起，丢弃流中指定数量的元素
 */
public class Demo09_Iterate {
    // 只能保存结果,需要一个变量来存储上一个变量值
    // 注意：lambda表达式需要外部局部变量为final类型（防止外部局部变量被释放掉）
    // 当外部变量为对象成员变量或类成员变量时，可以不为 final
    private static int x = 1;

    Stream<Integer> numbers() {

        // 第 1 个参数是初始值，
        // 第 2 个参数是 UnaryOperator<T>，用于计算下一个元素
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }

    public static void main(String[] args) {
        new Demo09_Iterate().numbers()
                .skip(1) // 跳过前 1 个
                .limit(10) // 然后取 10 个
                .forEach(System.out::println);


    }

}
