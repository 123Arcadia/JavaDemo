package TestThread.thread01.Lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 实现的接口是 ReadWriteLock (readLock和writeLock)
 */
public class ReadWriteLockTest {
    //读写锁
//    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    //获取写锁
    private final Lock wlock = rwLock.writeLock();
    //获取读锁
    private final Lock rLock = rwLock.readLock();

//    ReentrantLock

    /**
     * CopyOnWriteArrayList 修改时先clone在覆盖更新：
     * 缺点：（1）内存占用大（2）保证最终一致性，不保证实时一致性
     */
//new CopyOnWriteArrayList<Integer>()


    /**
     * 读锁不能升级为写锁(该例子的演示)
     * 写锁可以降级为读锁（写锁释放之前，获取读锁，达到锁降级）
     */
    @Test
    public void test() {
        //读写锁
        final java.util.concurrent.locks.ReadWriteLock rwLock = new ReentrantReadWriteLock();
        //获取写锁
        final Lock wlock = rwLock.writeLock();
        //获取读锁
        final Lock rLock = rwLock.readLock();
        //加锁

        rLock.lock();
        System.out.println("读锁开始工作");
        wlock.lock(); // 会堵塞
        System.out.println("写锁开始工作");
        rLock.unlock();
        wlock.unlock();
    }
}
