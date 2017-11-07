package cn.cdz.java8.utils;

import cn.cdz.java8.fork.ForkJoinSumCalculator;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * User: Cdz
 * Date: 2017/8/22
 * Time: 10:47
 */
public class MyJava8Utiles {

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
