package cn.cdz.thread.Sync;

import org.junit.Test;

/**
 * Created by CDz_ on 2017/11/27.
 * 同步代码块
 *
 * 多线程并发，一个线程保证一块代码执行完后
 * 才被会执行别的线程中的代码。（必须synchronized的锁对象要是同一对象，不能是匿名对象，匿名对象不是同一对象）
 */
public class SyncThreadTest{


    @Test
    public void testSynThread(){
        System.out.println("test开始");
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

        System.out.println("test结束");
    }

    public static void main(String[] args) {
        System.out.println("test开始");
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

        System.out.println("test结束");
    }
    static class Printer{
        String s = new String();
        public void print1(){
            synchronized (s){
                System.out.print("1");
                System.out.print("2");
                System.out.print("3");
                System.out.println();
            }
        }

        public void print2(){
            synchronized (s){
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.println();
            }
        }
    }
}
