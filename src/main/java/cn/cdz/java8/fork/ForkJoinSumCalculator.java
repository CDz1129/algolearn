package cn.cdz.java8.fork;

import java.util.concurrent.RecursiveTask;

/**
 * User: Cdz
 * Date: 2017/8/22
 * Time: 10:13
 * 使用分支/合并框架执行并行求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {//继承RecursiveTask来创建可以用于分支/合并框架的任务
    //要求和的数组
    private final long[] numbers;
    //子任务处理的数组的起始和终止位置
    private final int start;
    private final int end;

    //临界值（阈值）不再将任务分解为子任务的数组大小
    public static final long THRESHOLD = 10_000;

    //公共的构造函数用于创建主任务
    public ForkJoinSumCalculator(long[] numbers){
        this(numbers,0,numbers.length);
    }
    //私有构造函数用于以递归的方式为主任务创建子任务
    private ForkJoinSumCalculator(long[] numbers,int start,int end){
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    //覆盖    RecursiveTask其抽象方法
    @Override
    protected Long compute() {
        //该任务 负责求和部分 的大小
        int lenth = end - start;
        if (lenth <= THRESHOLD){
            //如果长度小于阈值，顺序执行
            return computeSequentially();
        }

        //将任务分解成两半
        //前一半 左边子任务
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + lenth / 2);
        //利用另一个forkjoinpool 线程异步执行新创建的子任务
        leftTask.fork();
        //创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers,start + lenth/2,end);
        //同步执行第二个子任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果，如果尚未完成就等待
        Long leftResult = leftTask.join();

        return leftResult+rightResult;//该任务的结果是两个子任务结果的组合
    }

    //子任务不能再继续分解时，计算结果的简单算法
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum+=numbers[i];
        }
        return sum;
    }
}
