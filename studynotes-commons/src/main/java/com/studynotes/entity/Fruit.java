package com.studynotes.entity;

import lombok.Data;

/**
 * Description:
 */

public interface Fruit {

    @Data
    class Orange {
    }

    @Data
    class Apple {

        private static long counter;

        private final long id = counter++;
    }

    class GrannySmith extends Apple {
    }

    class Gala extends Apple {
    }

    class Fuji extends Apple {
    }

    class Braeburn extends Apple {
    }
}
