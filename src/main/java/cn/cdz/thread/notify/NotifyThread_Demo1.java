package cn.cdz.thread.notify;

/**
 * @author CDz_
 * @create 2017-11-28 14:44
 * 案例场景，因为CPU执行是无序的，通过wait notify notifyall 控制多线程，使其有序
 **/
public class NotifyThread_Demo1 {

    public static void main(String[] args) {
        Printor printor = new Printor();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    printor.print1();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    printor.print2();
                }
            }
        }.start();


    }

}

class Printor {
    private int flag = 1;
    public synchronized void print1() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(1);
        System.out.print(2);
        System.out.print(3);
        System.out.print(4);
        System.out.print(5);
        System.out.print(6);
        System.out.println();

        this.flag = 2;
        this.notify();
    }

    public synchronized void print2() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("a");
        System.out.print("b");
        System.out.print("c");
        System.out.print("d");
        System.out.print("e");
        System.out.print("f");
        System.out.println();

        flag = 1;
        this.notify();
    }
}