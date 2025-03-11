package com.meiqiu.bestCaseV1.实现缓存的LFU_最不经常使用;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 实现缓存的 LFU，设定一个容量，存放缓存是，提前判断缓存是否会满，如要会满，删除使用频率最低的缓存数据，如果是多个，则删除最不经常使用的缓存。
 * @Author 胖辉
 * @Date 2025/3/7
 * @Time 13:47
 */
public class Test {

    //缓存的容量
    private static int capacity;
    //缓存 key 的使用频率
    private static Map<Integer, Integer> frequencyMap;
    //缓存 key 的最后使用时间
    private static Map<Integer, LocalDateTime> timeMap;
    //缓存
    private static Map<Integer, Integer> cache;

    /**
     * 初始化缓存
     */
    public static void LFUCache(int n) {
        capacity = n;
        frequencyMap = new HashMap<>();
        timeMap = new HashMap<>();
        cache = new HashMap<>();
    }

    public static int get(Integer key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        //更新 key 的使用频率和最后使用时间
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
        timeMap.put(key, LocalDateTime.now());
        return cache.get(key);
    }

    public static void put(Integer key, Integer value) {
        //如果缓存存在，更新缓存
        if (cache.containsKey(key)) {
            cache.put(key, value);
        }
        //如果缓存不存在，判断缓存是否已满
        if (cache.size() >= capacity) {
            return;
        }
        //将满
        if (cache.size() + 1 == capacity) {
            //找到使用频率最低的缓存数据
            //获取 frequencyMap 中 values 中小的 keys
            List<Integer> minFrequencyKeys = frequencyMap.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(Collections.min(frequencyMap.values())))
                    .map(Map.Entry::getKey)
                    .toList();
            //通过timeMap，查询minFrequencyKeys中最后使用时间最小的key
            LocalDateTime minTime = LocalDateTime.now();
            Integer minKey = null;
            for (Integer item : minFrequencyKeys) {
                if(timeMap.get(item).isBefore(minTime)){
                    minTime = timeMap.get(item);
                    minKey = item;
                }
            }
            cache.remove(minKey);
            frequencyMap.remove(minKey);
            timeMap.remove(minKey);
            cache.put(key, value);
        }
    }
}
