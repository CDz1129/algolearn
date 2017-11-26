package cn.cdz.java8;

import cn.cdz.java8.bean.Apple;
import cn.cdz.java8.bean.MathFun;
import jdk.management.resource.ResourceId;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.*;

/**
 * User: Cdz
 * Date: 2017/5/23
 * Time: 10:13
 */
public class LambdaDemo1 {
    public static void main(String[] args)  {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("红色", 150));
        apples.add(new Apple("绿色", 120));
        apples.add(new Apple("黄色", 110));
        apples.add(new Apple("绿色", 130));
        apples.add(new Apple("蓝色", 140));
        apples.add(new Apple("红色", 160));
        apples.add(new Apple("红色", 180));
        apples.add(new Apple("红色", 190));
////        Arrays.asList()
//        prettyPrintApple(apples, apple ->  {return (apple.getColor().equals("红色") ? "这是个红色苹果" : "");}    );
//        System.out.println("--------------------");
//
//
//
//        apples.sort((a1,a2) -> a1.getWeight().compareTo(a2.getWeight()));
//        System.out.println(apples);
//        Comparator<Apple> appleComparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));




        try {
            String s = processorFile(bufferedReader -> bufferedReader.readLine());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> integers = Arrays.asList(3, 2, 5, 7, 9, 10);


        integers.sort((s1,s2)->s1.compareTo(s2));
        integers.sort(Integer::compareTo);


        Supplier<Apple> supplier= Apple::new;
        BiFunction<String,Integer,Apple> runnable = Apple::new;
        Apple a = runnable.apply("s", 1);
        Apple o = supplier.get();

        List<Integer> filter = filter(integers, integer -> integer > 5);

        System.out.println(integers);
        System.out.println(filter);



        ArrayList<Integer> integers1 = new ArrayList<>();

        for (Integer integer : integers) {
            if (integer>5){
                integers1.add(integer);
            }
        }
        integers.forEach(integer -> { if (integer>5) integers1.add(integer);});

        int num = 1;
        forEach(integers,integer -> System.out.println(integer+num));

        //这样做会报错，原因在于，局部变量 进入lambda表达式 作为运算后 ，会将其隐式变成 final 最终的
        // 这是因为 ， 由于局部比变量是在 栈中存储，而lambda 可以看成是一个方法 这个方法里的局部变量在方法结束时会被释放。所以lambda将其设置为final、
//        num = 10;


        List<Integer> map = map(Arrays.asList("asdads", "a123", "qwe"), s -> s.length());
        System.out.println(map);

        DoubleFunction<Double> fun1 = MathFun::fun1;
        Double apply = fun1.apply(1);
        MathFun mathFun = new MathFun();

        double integrate = integrate(MathFun::fun1, 3, 7);
        System.out.println(integrate);


    }

    public static void prettyPrintApple(List<Apple> apples,ApplePredicate applePredicate){
        for (Apple apple : apples) {
            String test = applePredicate.test(apple);
            System.out.println(test);
        }
    }


    public static String processorFile(BufferReaderProcessor p) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
           return p.processs(br);
        }
    }

    //利用Java8 提供的predicate（谓语）接口 其接口只有一个test方法，接收任何参数 返回 boolean类型。
    //写出一个 集合过滤器
    public static <T>List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)){
                results.add(t);
            }
        }
        return results;
    }

    //利用Java8 提供的consumer（消费、消费者）接口 其接口只有一个accept方法接收任何参数 无返回参数
    public static <T>void forEach(List<T> list, Consumer<T> c){
        for (T t : list) {
            c.accept(t);
        }
    }
    //永华Java8 提供Function<T,R>（方法）接口 其接口只有一个apply方法 其作用是 传入一个类型 返回 不同类型
    public static <T,R>List<R> map(List<T> list , Function<T,R> f){
        List<R> results = new ArrayList<>();
        list.forEach(t -> results.add(f.apply(t)));
        return results;
    }


    public static double integrate(DoubleFunction<Double> f,double a, double b){
        return (f.apply(a)+f.apply(b))*(b-a)/2;
    }

}
