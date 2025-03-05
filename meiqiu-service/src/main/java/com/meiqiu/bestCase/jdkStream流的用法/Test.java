package com.meiqiu.bestCase.jdkStream流的用法;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description stream流用法
 * @Author 胖辉
 * @Date 2025/3/4
 * @Time 15:13
 */
public class Test {

    /**
     * 1、数据筛选与过滤：过滤年龄大于30的员工
     */
    public static void filter() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(25, "Alice"));
        employees.add(new Employee(35,"tob"));
        employees.add(new Employee(40,"kkk"));

        List<Employee> filteredEmployees = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .collect(Collectors.toList());
        for (Employee employee : filteredEmployees) {
            System.out.println(employee.getAge());
        }
    }

    /**
     * 2、数据映射和转换：将集合中的元素，全部转换为大写
     */
    public static void transUpperCase() {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        List<String> uppercaseNames = names.stream()
                .map(String::toUpperCase)
                .map(name -> name + "@qq.com")
                .collect(Collectors.toList());
        for (String name : uppercaseNames) {
            System.out.println(name);
        }

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(25, "Alice"));
        employees.add(new Employee(35,"tob"));
        employees.add(new Employee(40,"kkk"));
        List<String> nameList = employees.stream().map(Employee::getName).collect(Collectors.toList());
        //名称当 key，对象当值
        Map<String,Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getName, employee -> employee));


    }

    /**
     * 3、数据排序
     */
    public static void sort() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(3);
        numbers.add(7);
        numbers.add(3);
        numbers.add(1);

        List<Integer> sortList = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        for (Integer integer : sortList) {
            System.out.println(integer);
        }
    }

    /**
     * 4、数据聚合: 求和、平均值、最大值、最小值等
     */
    public static void aggregation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        IntSummaryStatistics stats = numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println("总和: " + stats.getSum());
        System.out.println("平均值: " + stats.getAverage());
        System.out.println("最大值: " + stats.getMax());
        System.out.println("最小值: " + stats.getMin());
    }
}

