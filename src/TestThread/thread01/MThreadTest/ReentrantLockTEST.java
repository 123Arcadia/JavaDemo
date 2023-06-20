package TestThread.thread01.MThreadTest;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTEST {
    public static void main(String[] args) {
        Window w1 = new Window();
        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();
    }
}
class Window implements Runnable{
    private int ticket = 100;
    //1、实例化 ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                //2、调用锁定方法lock()
                lock.lock();
                if (ticket > 0){
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "卖票，票号为:" + ticket);
                    ticket--;
                }else {
                    System.out.println("票已经卖完了");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //3、调用解锁方法 : unlock()
                lock.unlock();
            }
        }
    }
}