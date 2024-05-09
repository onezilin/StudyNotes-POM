package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

/**
 * Description:
 * <p>
 * 146. LRU 缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。实现 LRUCache 类：
 */
public class DemoLRU_146 {

    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }

    /**
     * Description: 解题思路：
     * 1、LRU（Least Recently Used）是指【最近最少使用】，即在容量满了的情况下，删除最近最少使用的元素。
     * 2、使用 LinkedHashMap 作为缓存，保证元素的读写有序性，同时保证增删改查时间复杂度为 O(1)。
     * 3、维护 LinkedHashMap，头部是最近最少使用的数据，尾部是最近添加或使用过的数据。数据读写时会被移动到尾部，当缓存满时，需要移除头部的数据。
     */
    class LRUCache {

        private int capacity;
        private LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new LinkedHashMap<>(capacity);
        }

        public int get(int key) {
            if (!cache.containsKey(key)) return -1;
            maintenaceCache(key);
            return cache.get(key);
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                maintenaceCache(key);
                cache.put(key, value);
            } else {
                // 如果缓存满了，移除头部元素
                if (cache.size() >= this.capacity) {
                    int removeKey = cache.keySet().iterator().next();
                    cache.remove(removeKey);
                }
                cache.put(key, value);
            }
        }

        private void maintenaceCache(int key) {
            int val = cache.get(key);
            cache.remove(key);
            cache.put(key, val);
        }
    }
}
