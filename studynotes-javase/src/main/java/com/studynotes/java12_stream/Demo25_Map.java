package com.studynotes.java12_stream;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

// map(Function) Optional中value不为空时,则使用函数对元素进行操作,将返回值自动包装成Optional
// value为null时，调用empty返回value为null的Optional.empty
/**
 * Description: map()，接收 Function 参数，
 * 当 Optional 中的 value 不为 null 时，使用 Function 对其进行处理，
 * value为null时，会返回一个 Optional.empty()
 */
public class Demo25_Map {
    static String[] elements = {"12", "", "23", "45"};

    static Stream<String> testStream() {
        return Arrays.stream(elements);
    }

    static void test(String descr, Function<String, String> func) {
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    testStream()
                            .skip(i)
                            .findFirst() // Produces an Optional
                            .map(func));
        }
    }

    public static void main(String[] args) {
        // If Optional is not empty, map() first extracts
        // the contents which it then passes
        // to the function:
        test("Add brackets", s -> "[" + s + "]");
        test("Increment", s -> {
            try {
                return Integer.parseInt(s) + 1 + "";
            } catch (NumberFormatException e) {
                return s;
            }
        });
        test("Replace", s -> s.replace("2", "9"));
        test("Take last digit", s -> s.length() > 0 ?
                s.charAt(s.length() - 1) + "" : s);
    }
}
