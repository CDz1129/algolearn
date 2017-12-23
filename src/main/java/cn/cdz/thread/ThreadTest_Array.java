package cn.cdz.thread;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author CDz_
 * @create 2017-12-04 16:14
 **/
public class ThreadTest_Array {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> integers = new ConcurrentLinkedQueue<>();
        Thread a = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    integers.add(i);
                    System.out.println("a线程在执行");
                }
            }
        };
        Thread b = new Thread() {
            @Override
            public void run() {
                for (int i = 20; i < 60; i++) {
                    integers.add(i);
                    System.out.println("b线程再执行");
                }
            }
        };
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        ArrayList<Integer> integers = new ArrayList<>();
//        integers.parallelStream().


        System.out.println(integers.toString());
    }
}
