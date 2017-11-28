package cn.cdz.thread.notify;

/**
 * @author CDz_
 * @create 2017-11-28 21:58
 * 三个线程或三个线程以上通信
 **/
public class NotifyAll_Demo1 {
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
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    printor.print3();
                }
            }
        }.start();

    }

    static class Printor {
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
            this.notifyAll();
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

            flag = 3;
            this.notifyAll();
        }

        public synchronized void print3() {
            while (flag != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print("A");
            System.out.print("B");
            System.out.print("C");
            System.out.print("D");
            System.out.print("E");
            System.out.print("F");
            System.out.println();

            flag = 1;
            this.notifyAll();
        }
    }

}
