package com.studynotes.java17_container;

import com.studynotes.entity.Fruit;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * Description: 继承AbstractCollection需要实现两个方法
 */
public class Demo20_AbstractCollection {

    class CollectionSequence extends AbstractCollection<Fruit.Apple> {
        private Fruit.Apple[] pets = IntStream.range(0, 10).mapToObj(i -> new Fruit.Apple()).toArray(i -> new Fruit.Apple[0]);

        @Override
        public int size() {
            return pets.length;
        }

        @Override
        public Iterator<Fruit.Apple> iterator() {
            return new Iterator<Fruit.Apple>() { // [1]
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < pets.length;
                }

                @Override
                public Fruit.Apple next() {
                    return pets[index++];
                }

                @Override
                public void remove() { // Not implemented
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}




