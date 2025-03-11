package com.meiqiu.bestCaseV1.获取文件中出现频率最高的单词;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        //输出多少个元素
        int count = 3;
        // 请将文件路径替换为你实际的文本文件路径
        String filePath = "your_file.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(filePath);
        // 将 Map 中的键值对转换为 List，以便进行排序
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordFrequency.entrySet());
        // 使用自定义比较器按频率从高到低排序
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        // 输出排序后的结果
        for (int i = 0; i < (Math.min(entryList.size(), count)); i++) {
            Map.Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static Map<String, Integer> countWordFrequency(String filePath) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 将行中的非字母字符替换为空格，并转换为小写
                String cleanLine = line.replaceAll("[^a-zA-Z]", " ").toLowerCase();
                // 按空格分割成单词数组
                String[] words = cleanLine.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        // 更新单词的频率
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return wordFrequency;
    }
}