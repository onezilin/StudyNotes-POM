package com.studynotes.java12_stream;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Description:
 */
public class Demo28_RandomPair {
    Random rand = new Random(47);
    // An infinite iterator of random capital letters:
    Iterator<Character> capChars = rand.ints(65, 91)
            .mapToObj(i -> (char) i)
            .iterator();

    public Stream<Pair> stream() {
        return rand.ints(100, 1000).distinct()
                .mapToObj(i -> new Pair(capChars.next(), i));
    }

    static class Pair {
        public Character c;
        public Integer i;

        Pair() {
            c = 'a';
            i = -1;
        }

        Pair(Character c, Integer i) {
            this.c = c;
            this.i = i;
        }

        public Character getC() {
            return c;
        }

        public Integer getI() {
            return i;
        }

        @Override
        public String toString() {
            return "Pair(" + c + ", " + i + ")";
        }
    }
}
