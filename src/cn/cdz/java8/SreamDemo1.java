package cn.cdz.java8;

import cn.cdz.java8.bean.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Cdz
 * Date: 2017/5/24
 * Time: 15:25
 */
public class SreamDemo1 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        List<String> collect = menu.stream()
                .filter(Dish::isVegetarian)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        //选出前两个 荤菜
        List<Dish> collect1 = menu.stream()
                .filter(dish -> dish.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .collect(Collectors.toList());

        String[] words = {"hello","world"};
        List<String> collect2 = Arrays.stream(words).map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect2);
        System.out.println(collect);
        //给出数字列表，返回每个数字的平方列表
        List<Integer> x = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> y = x.stream().map(s -> s * s).collect(Collectors.toList());
        System.out.println(y);
        //两个数字列表，返回其所有的数对。

        x.stream()
                //这里必须要使用，flatMap 原因在于，integer -> y.stream().map(j -> new int[]{integer, j})其返回的不是 每个数组的流，而是 6个数据一起连接起来的流，
                //所以需要将其全部切断，将所有的 单个int[]变成单个流。然后组合成一个整体流
                .flatMap(integer -> y.stream()
                        //在此时加就可以在生成数组的前面就定义好的规则
                                    .filter(j->(integer+j)%3==0)
                                    .map(j -> new int[]{integer, j}))
                //在这里filter 并不好 的原因在于 其实已经将所有的元素都拿出来了，并没有用到起最佳
//                .filter(ints -> (ints[0]+ints[1])%3==0)
                .collect(Collectors.toList())
                .forEach(ints -> System.out.println(ints[0]+","+ints[1]));

        Integer reduce = x.stream().reduce(1, (a, b) -> a + b);
        System.out.println(reduce);

        Integer reduce1 = menu.stream().map(dish -> 1).reduce(0, (a, b) -> a + b);
        long count = menu.stream().count();
        System.out.println(count);
        System.out.println(reduce1);


        //归约和汇总
        IntSummaryStatistics summaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);
    }

}
