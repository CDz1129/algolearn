package cn.cdz.thread.Sync;

/**
 * Created by CDz_ on 2017/11/27.
 * 多线程售票
 * 实现Runnable接口写法
 */
public class SyncThread_Demo1 {

    public static void main(String[] args) {
        TicketThread ticketThread = new TicketThread();
        Thread a = new Thread(ticketThread,"A");
        Thread b = new Thread(ticketThread,"B");
        Thread c = new Thread(ticketThread,"C");
        Thread d = new Thread(ticketThread,"D");
        a.start();
        b.start();
        c.start();
        d.start();
    }
    static class TicketThread implements Runnable {
        private int ticket = 100;
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                synchronized (this) {
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
