package com.studynotes.java12_stream;

/**
 * Description:
 * forEach()：参数是一个Consumer函数，用于消费Stream中的数据
 * parallel()：将流中元素分割成处理器个数份，交由不同处理器处理。速度可能会更快，但是可能会导致流中元素不按照顺序操作
 * forEachOrdered()：用于强制保持流中流中顺序为原始顺序
 */
public class Demo32_ForEach {
    static final int SZ = 14;

    public static void main(String[] args) {
        Demo31_ToArray.rands().limit(SZ)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println("-----------------");
        Demo31_ToArray.rands().limit(SZ)
                .parallel()
                .forEach(n -> System.out.format("%d ", n));
        System.out.println("------------------");
        Demo31_ToArray.rands().limit(SZ)
                .parallel()
                .peek((n) -> System.out.format("%d ", n))
                .forEachOrdered(n -> System.out.format("%d ", n));
        // 258 555 693 861 961 429 868 200 522 207 288 128 551 589
    }
}
