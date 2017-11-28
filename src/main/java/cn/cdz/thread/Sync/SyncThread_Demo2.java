package cn.cdz.thread.Sync;

/**
 * 多线程售票
 * 继承Thread类
 */
public class SyncThread_Demo2 {

    public static void main(String[] args) {
        new TicketThread("A").start();
        new TicketThread("B").start();
        new TicketThread("C").start();
        new TicketThread("D").start();
    }
    static class TicketThread extends Thread{
        private static int ticket = 100;

        public TicketThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                synchronized (TicketThread.class) {
                    if (ticket <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + "线程窗口售出，一共还剩：" + ticket-- + "张票");
                }
            }
        }
    }
}
