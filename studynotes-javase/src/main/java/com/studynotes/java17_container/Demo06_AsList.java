package com.studynotes.java17_container;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description: Arrays.asList传入的数组，其实底层实现还是数组，当进行add()/remove()时都会改变数组的大小，会报错
 */
public class Demo06_AsList {
    class Snow {
    }

    class Powder extends Snow {
    }

    class Light extends Powder {
    }

    class Heavy extends Powder {
    }

    class Crusty extends Snow {
    }

    class Slush extends Snow {
    }

    @Test
    public void asListTest() {
        List<Snow> snow1 = Arrays.asList(
                new Crusty(), new Slush(), new Powder());
        // 进行新增操作时抛异常
        // snow1.add(new Heavy());

        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3,
                new Light(), new Heavy(), new Powder());
        snow3.add(new Crusty());
    }
}

