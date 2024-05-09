package com.studynotes.demo01_leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Description:
 * <p>
 * 460. LFU 缓存
 * <p>
 * 请你为最不经常使用（LFU）缓存算法设计并实现数据结构。
 */
public class DemoLFU_460 {

    @Test
    public void test() {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4, 4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }

    /**
     * Description: 解题思路：
     * 1、LFU（Least Frequently Used）是指【最近最不经常使用】，即在容量满了的情况下，删除最少使用的元素。
     * 2、由于不需要记录元素的添加顺序，所以不需要使用 LinkedHashMap，直接使用 HashMap 作为缓存即可。
     * 3、使用 freq 哈希表记录每个 key 对应的使用频率，每次读写操作都需要维护 freq，容量满时，删除最小使用频率的 key。
     * 4、使用 freqCount 哈希表记录相同频率的 key 的集合，每次读写操作都需要维护 freq，容量满时，并且最小使用频率对应多个 key，按照 LRU 进行删除
     * 5、难点在于删除 key 时，也要维护 freq 和 freqCount
     */
    class LFUCache {

        private final int capacity;
        private final Map<Integer, Integer> cache; // 存储 key-value
        private final Map<Integer, Integer> freq = new HashMap<>(); // 记录每个 key 对应的使用频率
        private final Map<Integer, LinkedHashSet<Integer>> freqCount = new TreeMap<>(); // 记录相同频率的 key 的集合，方便 LRU 进行删除

        public LFUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(capacity);
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
                // 如果缓存满了，移除使用频率最低的元素
                if (cache.size() >= capacity) {
                    Integer minCount = freqCount.keySet().iterator().next();
                    LinkedHashSet<Integer> commonCountKey = freqCount.get(minCount);
                    Integer needRemove = commonCountKey.iterator().next();
                    commonCountKey.remove(needRemove);
                    if (commonCountKey.isEmpty()) {
                        freqCount.remove(minCount);
                    }
                    cache.remove(needRemove);
                    freq.remove(needRemove);
                }
                maintenaceCache(key);
                cache.put(key, value);
            }
        }

        private void maintenaceCache(int key) {
            int originCount = freq.getOrDefault(key, 0);
            int newCount = originCount + 1; // 更新频率
            freq.put(key, newCount);

            // 更新 freqCount
            if (freqCount.containsKey(newCount)) {
                LinkedHashSet<Integer> queue = freqCount.get(newCount);
                queue.add(key);
            } else {
                LinkedHashSet<Integer> queue = new LinkedHashSet<>();
                queue.add(key);
                freqCount.put(newCount, queue);
            }
            if (freqCount.containsKey(originCount)) {
                LinkedHashSet<Integer> queue = freqCount.get(originCount);
                queue.remove(key);
                if (queue.isEmpty()) {
                    freqCount.remove(originCount);
                }
            }
        }
    }
}
