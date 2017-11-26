package cn.cdz.java8;

import cn.cdz.java8.bean.Trader;
import cn.cdz.java8.bean.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: Cdz
 * Date: 2017/5/27
 * Time: 11:32
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年发生的所有交易，并按交易额排序（从低到高）
        transactions.stream()
                    .filter(transaction -> transaction.getYear() == 2011)
                    .sorted(Comparator.comparing(Transaction::getValue))
                    .collect(Collectors.toList())
                    .forEach(transaction -> System.out.println(transaction));

        System.out.println("-------------------------------------------");

        //交易员都在哪些不同城市工作过
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                //新招，distinct去掉 在collect（toset（））
                .distinct()
                .collect(Collectors.toList()).forEach(s -> System.out.println(s));
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                //新招，distinct去掉 在collect（toset（））
//                .distinct()
                .collect(Collectors.toSet())
                .forEach(s -> System.out.println(s));
        System.out.println("-------------------------------------------");


        //查找出所有来自剑桥的交易员，并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                //此时使用去重有效果的原因在于，transactions里面的transaction对应引用的trader是同一个变量（堆中只有一个trader），
                // 如果是两次分别new出来的，没有在其bean中重写 equals与hashcode方法是不会去重的
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(trader -> trader.getName()))
                .collect(Collectors.toList())
                .forEach(trader -> System.out.println(trader));
        System.out.println("-------------------------------------------");

        //返回所有交易员的姓名字符串，按字母的顺序排列
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList()).forEach(s -> System.out.println(s));
        System.out.println("-------------------------------------------");

        //有没有交易员是在米兰工作的
        //1
        Optional<Trader> milan = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .filter(s -> s.getCity().equals("Milan"))
                .findAny();
        //2
        boolean milan1 = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        //3
        boolean milan2 = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println("方式1："+milan.isPresent());
        System.out.println("方式2：" + milan1);
        System.out.println("方式3：(此方法最效率)" + milan2);
        System.out.println("-------------------------------------------");

        //打印生活在剑桥的交易员的所有交易额
        Integer cambridge = transactions.stream()
                //transaction.getTrader().getCity().equals("Cambridge")这种写法容易出现空指针异常，
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(transaction -> transaction.getValue())
                .reduce(0, (a, b) -> a + b);
        System.out.println("生活在剑桥的交易员的所有交易额="+cambridge);
        System.out.println("-------------------------------------------");

        //所有交易额中最高的
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        max.ifPresent(integer -> System.out.println("所有交易额中最高的:"+integer));
        System.out.println("-------------------------------------------");

        //所有交易额中最小
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        min.ifPresent(integer -> System.out.println("所有交易额中最高的:"+integer));

        System.out.println("流可以支持min和max方法，接受一个comparator");

        Optional<Transaction> min1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        min1.ifPresent(transaction -> System.out.println(transaction));
        System.out.println("-------------------------------------------");


        //数值流
        //mapToInt返回的是一个intstream！
        //intstream其表示为int类型的stream流
        //其他stream是用泛型，来表示是什么类型的流
        IntStream intStream = transactions.stream().mapToInt(Transaction::getValue);
        //使用IntStream里面max方法返回的OptionalInt其实是有默认值的
        OptionalInt max1 = intStream.max();
        //orElse方法是如果没有最大值，指定返回值
        int i = max1.orElse(1);
        //其基础类型stream都有包装的stream类
//        int sum = intStream.sum();


        //使用intstream来 得到1-100中的偶数
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        long count = evenNumbers.count();
        System.out.println(evenNumbers);

        //获得勾股流 方法1
        Date begin = new Date(System.currentTimeMillis());
        Stream<int[]> pythagoreans = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        Date end = new Date(System.currentTimeMillis());
        pythagoreans.limit(5).forEach(t-> System.out.println(t[0]+", "+t[1]+", "+t[2]));
        System.out.println("one:"+(end.getTime()-begin.getTime()));
        System.out.println("-------------------------------------------");
        // 方法2
        begin = new Date(System.currentTimeMillis());
        Stream<double[]> pythagoreans2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b , Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );
        end = new Date(System.currentTimeMillis());
        pythagoreans2.limit(5).forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));
        System.out.println("two:"+(end.getTime()-begin.getTime()));


    }

}

