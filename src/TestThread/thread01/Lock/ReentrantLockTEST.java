package TestThread.thread01.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 主要方法:
 *     getHoldCount()：当前线程调用 lock() 方法的次数。
 *     getQueueLength()：当前正在等待获取 Lock 锁的线程的估计数。
 *     getWaitQueueLength(Condition condition)：当前正在等待状态的线程的估计数，需要传入 Condition 对象。
 *     hasWaiters(Condition condition)：查询是否有线程正在等待与 Lock 锁有关的 Condition 条件。
 *     hasQueuedThread(Thread thread)：查询指定的线程是否正在等待获取 Lock 锁。
 *     hasQueuedThreads()：查询是否有线程正在等待获取此锁定。
 *     isFair()：判断当前 Lock 锁是不是公平锁。
 *     isHeldByCurrentThread()：查询当前线程是否保持此锁定。
 *     isLocked()：查询此锁定是否由任意线程保持。
 *     tryLock()：线程尝试获取锁，如果获取成功，则返回 true，如果获取失败（即锁已被其他线程获取），则返回 false。
 *     tryLock(long timeout，TimeUnit unit)：线程如果在指定等待时间内获得了锁，就返回true，否则返回 false。
 *     lockInterruptibly()：如果当前线程未被中断，则获取该锁定，如果已经被中断则出现异常。
 */
public class ReentrantLockTEST {
    public static void main(String[] args) {
        WindowReenLock w1 = new WindowReenLock();
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
class WindowReenLock implements Runnable{
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