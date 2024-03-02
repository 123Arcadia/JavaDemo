package TestThread.thread01.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockAwait_Singal_Condition {
    private static boolean hasCigarette = false;
    private static boolean hasTakeout = false;
    private static final ReentrantLock lock = new ReentrantLock();

    // 等待烟的休息室（条件变量）
    static Condition waitCigaretteSet = lock.newCondition();
    // 等外卖的休息室（条件变量）
    static Condition waitTakeoutSet = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.printf("有烟没？[%b]\n", hasCigarette);
                while (!hasCigarette) {
                    System.out.println("没烟，先歇会！");
                    try {
                        System.out.println("[释放前BEdemo1]" + toStringReentanLock(lock, waitCigaretteSet, Thread.currentThread()));
                        // 此时小南进入到 等烟的休息室
                        waitCigaretteSet.await();
                        System.out.println("[释放后AFdemo1]" + toStringReentanLock(lock, waitCigaretteSet, Thread.currentThread()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("烟来咯, 可以开始干活了");
            } finally {
                lock.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.printf("外卖送到没？[%b]\n", hasTakeout);
                while (!hasTakeout) {
                    System.out.println("没外卖，先歇会！");
                    try {
                        System.out.println("[释放前BEdemo2]" + toStringReentanLock(lock, waitTakeoutSet, Thread.currentThread()));

                        // 此时小女进入到 等外卖的休息室
                        waitTakeoutSet.await();
                        System.out.println("[释放前AFdemo2]" + toStringReentanLock(lock, waitTakeoutSet, Thread.currentThread()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("外卖来咯, 可以开始干活了");
            } finally {
                lock.unlock();
            }
        }, "小女").start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("送外卖的来咯~");
                hasTakeout = true;
                // 唤醒等外卖的小女线程
                waitTakeoutSet.signal();
            } finally {
                lock.unlock();
            }
        }, "送外卖的").start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("送烟的来咯~");
                hasCigarette = true;
                // 唤醒等烟的小南线程
                waitCigaretteSet.signal();
            } finally {
                lock.unlock();
            }
        }, "送烟的").start();
        //有烟没？[false]
        //没烟，先歇会！
        //[释放前BEdemo1]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread 小南], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=1, hasQueuedThreads=true, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //外卖送到没？[false]
        //没外卖，先歇会！
        //[释放前BEdemo2]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread 小女], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //送外卖的来咯~
        //[释放前AFdemo2]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread 小女], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //外卖来咯, 可以开始干活了
        //送烟的来咯~
        //[释放后AFdemo1]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread 小南], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //烟来咯, 可以开始干活了
    }

    /**
     *
     * @param lock
     * @param lockCondition
     * @param curThread
     * @return
     */
    public static String toStringReentanLock(ReentrantLock lock, Condition lockCondition, Thread curThread) {
        StringBuilder sb = new StringBuilder();
        sb.append(lock.toString()).append(", isFair: ").append(lock.isFair())
                .append(", isHeldByCurrentThread: ").append(lock.isHeldByCurrentThread())
                .append(", HoldCount=").append(lock.getHoldCount())
                .append(", QueueLength=").append(lock.getQueueLength())
                .append(", hasQueuedThreads=").append(lock.hasQueuedThreads());


        if (lockCondition!=null) {
            sb.append(", hasWaiters(lockCondition)=").append(lock.hasWaiters(lockCondition));
            if (curThread!=null) {
                sb.append(", hasQueuedThread(curThread)=").append(lock.hasQueuedThread(curThread))
                        .append(", hasQueuedThread(curThread)=").append(lock.hasQueuedThread(curThread));
            }
        }
        return sb.toString();
    }

}
