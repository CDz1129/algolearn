package cn.cdz.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * User: Cdz
 * Date: 2017/5/31
 * Time: 11:10
 * 构建流
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        //静态方法 of
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        //构建空流
        Stream<Object> empty = Stream.empty();

        //由数组构建流
        int[] nums = new int[]{1,2,3,4,5,6};
        int sum = Arrays.stream(nums).sum();
        System.out.println(sum);
        System.out.println("-----------------------------");

        long uniqueWords = 0;
        //利用nio操作文件读取，nio中很多静态方法返回一个stream
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            //nio在Files的静态方法lines paths对象与charset对象，流会自动关闭
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (IOException e){
            System.out.println("读取data.txt文件失败。");
        }
        System.out.println("data.txt文件中词汇数有："+uniqueWords);
        System.out.println("-----------------------------");

        //斐波那契数列，既是初始两个数，后面每个数都是前面两个数的和,
        //利用数组对来做，首先初始数组对{0,1}，后面每一个数组都是{t[1],t[0]+t[1]}，这样就得到的每一对，我们需要的是每一个数组中的第一个
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .map(t-> t[0])
                .forEach(t-> System.out.println(t));

    }
}
