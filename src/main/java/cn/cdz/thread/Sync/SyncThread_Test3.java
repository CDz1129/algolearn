package cn.cdz.thread.Sync;

/**
 * Created by CDz_ on 2017/11/27.
 * 静态同步方法
 */
public class SyncThread_Test3 {

    static class Printer {

        public static synchronized void print1() {
            System.out.print("1");
            System.out.print("2");
            System.out.print("3");
            System.out.println();
        }

        public static synchronized void print2() {
                System.out.print("a");
                System.out.print("b");
                System.out.print("c");
                System.out.println();
        }

        public static void main(String[] args) {
            System.out.println("test-start");
            Printer printer = new Printer();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        printer.print1();
                    }
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        printer.print2();
                    }
                }
            }.start();
            System.out.println("test-end");

        }
    }
}
