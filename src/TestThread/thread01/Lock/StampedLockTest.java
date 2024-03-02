package TestThread.thread01.Lock;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock有以下3种模式：
 * <p>
 * 1. 悲观读锁：与ReadWriteLock的读锁类似（这里的读锁不可重入），多个线程可以同时获取悲观读锁，悲观读锁是一个共享锁。
 * 2. 乐观读锁：相当于直接操作数据，不加任何锁，连读锁都不要。在操作数据前并没有通过CAS 设置锁的状态，仅仅通过位运算测试。如果当前没有线程持有写锁 ，则简单地返回 一个非 0 的 stamp 版本信息 ，返回0则说明有线程持有写锁。 获取该 stamp 后在具体操作数据前还需要调用validate 方法验证该 s tamp 是否己经不可用
 * 3. 写锁：与ReadWriteLock的写锁类似，写锁和悲观读锁是互斥的。虽然写锁与乐观读锁不会互斥，但是在数据被更新之后，之前通过乐观读锁获得的数据已经变成了脏数据。是 一 个排它锁或者独占锁，某时只有 一 个线程可以获取该锁，当二个线程获取该锁后，其他请求读锁和写锁的线程必须 等待 ，这类似于ReentrantReadWriteLock 的写锁（不同点在于这里的写锁不可重入）
 * <p>
 * <p>
 * StampedLock 的读写锁都是[不可重入锁]，所以在获取锁后释放锁前不应该再调用会获取锁的操作，以避免造成调用线程被阻塞
 */
public class StampedLockTest {
    //创建1个map 代表共享数据
    final static HashMap<String, String> MAP = new HashMap<>();
    //创建一个印戳锁
    final static StampedLock STAMPED_LOCK = new StampedLock();

    /**
     * 对共享数据的写操作
     */
    public static Object put(String key, String value) {
        long stamp = STAMPED_LOCK.writeLock();
        try {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": 抢占了写锁，开始写操作");
            String put = MAP.put(key, value);
            return put;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": 释放了写锁");
            STAMPED_LOCK.unlock(stamp);
        }
        return null;
    }

    /**
     * 对共享数据的悲观读操作
     */
    public static Object pessimisticRead(String key) {
        System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  进入过写模式，只能悲观读");
        long stamp = STAMPED_LOCK.readLock();
        try {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": 获取了读锁");
            String value = MAP.get(key);
            return value;
        } finally {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": 释放了读锁");
            STAMPED_LOCK.unlockRead(stamp);
        }
    }

    /**
     * 对共享数据的乐观读操作
     */
    public static Object optimisticRead(String key) {
        String value = null;
        /**
         * 代码1处stamp==0说明当前为写锁模式，只能使用悲观读
         * 代码2处： 乐观读已经过了一段时间，期间可能发生写入，所以验证乐观读的印戳值是否有效，即判断LOCK是否进入过写模式
         */
        long stamp = STAMPED_LOCK.tryOptimisticRead();
        if (stamp != 0) {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  乐观锁的印戳值获取成功");
            value = MAP.get(key);
        } else if (stamp == 0) { //代码1
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  乐观锁的印戳值获取失败，开始使用悲观读");
            return pessimisticRead(key);
        }
        if (!STAMPED_LOCK.validate(stamp)) {//代码2处
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  乐观读的印戳值已经过期");
            return pessimisticRead(key);
        } else {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  乐观读的印戳值没有过期");
            return value;
        }
    }

    public static Date getNowTime() {
        return new Date();
    }


    public static void main(String[] args) throws InterruptedException {
        MAP.put("initKey", "initValue");
        Thread t1 = new Thread(() -> {
            // 乐观读
            System.out.println(optimisticRead("initKey"));
        }, "读线程1");
        Thread t2 = new Thread(() -> {
            put("key1", "value1");
        }, "写线程1");
        // 写之后再乐观读
        Thread t3 = new Thread(() -> {
            System.out.println(optimisticRead("initKey"));
        }, "读线程2");
        t1.start();
        t1.join();
        t2.start();

        t3.start();
        Thread.sleep(1000);
        //读线程1, Sat Mar 02 21:08:01 CST 2024:  乐观锁的印戳值获取成功
        //读线程1, Sat Mar 02 21:08:01 CST 2024:  乐观读的印戳值没有过期
        //initValue
        //写线程1, Sat Mar 02 21:08:01 CST 2024: 抢占了写锁，开始写操作
        //读线程2, Sat Mar 02 21:08:01 CST 2024:  乐观锁的印戳值获取失败，开始使用悲观读
        //写线程1, Sat Mar 02 21:08:01 CST 2024: 释放了写锁
        //读线程2, Sat Mar 02 21:08:01 CST 2024:  进入过写模式，只能悲观读
        //读线程2, Sat Mar 02 21:08:01 CST 2024: 获取了读锁
        //读线程2, Sat Mar 02 21:08:01 CST 2024: 释放了读锁
        //initValue
        //
        //Process finished with exit code 0
    }

}
