package cn.cdz.thread;

import org.junit.Test;

/**
 * Created by CDz_ on 2017/11/26.
 */
public class ThreadTest {
    static class MyThreadOne extends Thread{
        public MyThreadOne(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println("线程："+getName()+"开始");
            for (int i = 0; i < 10; i++) {
                System.out.println(i+" "+getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程："+getName()+"结束");
        }

    }

    static class MyThreadTwo implements Runnable{
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("thread-name:"+name+"开始");
            for (int i = 0; i < 10; i++) {
                System.out.println(i+" "+name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("thread-name:"+name+"结束");
        }
    }

    public static void main(String[] args) {

    }

    @Test
    public void testJoin(){
        System.out.println(Thread.currentThread().getName()+"线程开始");
        MyThreadOne a = new MyThreadOne("a");
        MyThreadOne b = new MyThreadOne("b");
        a.start();
        b.start();
        try {
            b.join();
            //join 方法 如果没有传入时间时，当b线程结束之后才会执行下面的代码。
            //当传入时间时，由于在当时间过后，就会执行下面的代码
            //这说明：当使用join时，执行下面代码的条件只有，当时间过时，和调用此方法的线程结束时。
            System.out.println("join执行完了~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"线程结束");
    }

}
