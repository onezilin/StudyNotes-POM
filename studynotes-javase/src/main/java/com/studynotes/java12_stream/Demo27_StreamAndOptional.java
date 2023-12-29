package com.studynotes.java12_stream;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Description: 使用 Stream<Optional> 类型
 */
public class Demo27_StreamAndOptional {
    public static void main(String[] args) {
        Signal.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println(" ---");
        Signal.stream()
                .limit(15)
                .filter(Optional::isPresent)
                // Optional转化为Signal
                .map(Optional::get)
                .forEach(System.out::println);
    }

    static class Signal {
        private final String msg;

        public Signal(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "Signal(" + msg + ")";
        }

        static Random rand = new Random(47);

        public static Signal morse() {
            switch (rand.nextInt(4)) {
                case 1:
                    return new Signal("dot");
                case 2:
                    return new Signal("dash");
                default:
                    return null;
            }
        }

        public static Stream<Optional<Signal>> stream() {
            return Stream.generate(Signal::morse)
                    .map(signal -> Optional.ofNullable(signal));
        }
    }
}


