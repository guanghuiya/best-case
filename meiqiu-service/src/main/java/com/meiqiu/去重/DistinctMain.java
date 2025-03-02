package com.meiqiu.去重;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author sgh
 * @Date 2025/2/25
 * @Time 15:28
 */
public class DistinctMain {

    public static void main(String[] args) {
        //对象比较
        Item item1 = new Item(1, 1);
        Item item2 = new Item(1, 1);
        Item item3 = new Item(2, 1);
        System.out.println(item1.equals(item2));
        System.out.println(item1.equals(item3));

        System.out.println("==========equsla结束===========");

        //模拟数据
        List<Item> items = new ArrayList<>();
        items.add(new Item(1, 1));
        items.add(new Item(1, 2));
        items.add(new Item(1, 2));
        items.add(new Item(2, 1));
        items.add(new Item(2, 1));
        items.add(new Item(2, 2));
        items.add(new Item(2, 3));

        for (Item item : items) {
            System.out.println(item.getItemId() + "," + item.getSkuId());
        }

        System.out.println("===========模拟数据结束==========");

        //HashSet去重
        Set<Item> set =new HashSet<>();
        set.addAll(items);
        for (Item item : set) {
            System.out.println(item.getItemId() + "," + item.getSkuId());
        }

        System.out.println("==========HashSet去重结束===========");

        //lambda去重
        List<Item> res = items.stream()
                .distinct()
                .collect(Collectors.toList());
        for (Item item : res) {
            System.out.println(item.getItemId() + "," + item.getSkuId());
        }

        System.out.println("==========lambda去重结束===========");


    }
}
