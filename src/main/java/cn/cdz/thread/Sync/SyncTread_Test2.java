package cn.cdz.thread.Sync;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

/**
 * Created by CDz_ on 2017/11/27.
 *
 * 同步方法
 *
 * 当synchronized修饰一个方法时整个方法都是同步的。（非静态方法）
 *
 * 非静态方法锁对象是 this。其他代码块如果想要与其同步，需要用到this这个锁，既是其本身。
 *
 * 当同一个类中 两个方法都被 synchronized修饰时，其两方法是互斥的。
 */
public class SyncTread_Test2 {

    static class Printer{

        public synchronized void print1(){
            System.out.print("1");
            System.out.print("2");
            System.out.print("3");
            System.out.println();
        }


        public synchronized void print2(){
//            synchronized (this){
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.println();
//            }
        }
    }

    public static void main(String[] args) {
        System.out.println("test-start");
        Printer printer = new Printer();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    printer.print1();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    printer.print2();
                }
            }
        }.start();
        System.out.println("test-end");

    }
}
