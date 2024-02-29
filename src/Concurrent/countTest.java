package Concurrent;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 计数器: 在 Java 中，有两种常见的计数器实现方式：AtomicLong 和 LongAdder。
 *
 * 阿里巴巴在一份技术报告中推荐使用 LongAdder ，而不是 AtomicLong。
 */
public class countTest {
    /**
     * CAS的问题
     * CAS 有个缺点就是会带来 ABA 的问题。
     * 从 CAS 更新的时候，我们可以发现它只比对当前值和内存值是否相等，这会带来个问题，下面我举例说明下:
     *      假设线程 A 读到当前值是 10，可能线程 B 把值修改为 100，然后线程 C 又把值修改为 10。
     *      等到线程 A 拿到执行权时，因为当前值和内存值是一致的，线程A是可以修改的!
     *      站在线程 A 的角度来说，这个值是从未被修改的 。
     *      这是不合理的，因为我们从上帝的角度来看，这个变量已经被线程 B 和线程 C 修改过了。
     *
     * LongAdder 是 JDK1.8 由 Doug Lea 大神新增的原子操作类，位于 java.util.concurrent.atomic 包下，LongAdder 在高并发的场景下会比 AtomicLong 具有更好的性能，代价是消耗更多的内存空间。
     *
     * LongAdder 是 Google 开源的一个高性能计数器实现。它采用了一种分段锁的策略，将一个 long 型的变量分割成多个 16 字节的段，每个段都使用一个独立的 AtomicLong 进行更新。这样，在高并发场景下，多个线程可以同时对不同的段进行更新操作，互不干扰。
     *
     * LongAdder 的优点是并发性能高，适用于高并发的场景。由于采用了分段锁的策略，LongAdder 可以避免 AtomicLong 中的竞争问题。此外，LongAdder 还支持可扩展性，可以通过增加更多的段来提高性能。 但是，LongAdder的缺点是代码相对复杂一些，需要更多的维护成本。
     *
     */
    @Test
    public void testLongAdder() {
        int N = 1000_000;
        LongAdder longAdder = new LongAdder();

        StopWatch stopWatch = StopWatch.create();
        stopWatch.start();
        Thread[] threads = new Thread[10];
        // 每个线程100个加法操作
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < N; j++) {
                    longAdder.increment();
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println("timeOut:" + stopWatch.getTime(TimeUnit.MILLISECONDS));
        System.out.println("longAdder = " + longAdder);
        long sum = longAdder.sum();
        System.out.println("sum = " + sum);
        long sumThenReset = longAdder.sumThenReset();
        System.out.println("sumThenReset = " + sumThenReset);
        //timeOut:33
        //longAdder = 10000000
        //sum = 10000000
        //sumThenReset = 10000000
    }

    @Test
    public void testAtomInteger() {
        AtomicInteger atomInt = new AtomicInteger();
        int N = 1000_000;
        StopWatch stopWatch = StopWatch.create();
        stopWatch.start();
        Thread[] threads = new Thread[10];
        // 每个线程100个加法操作
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {
                for (int j = 0; j < N; j++) {
                    atomInt.incrementAndGet();
                }
            });
            threads[i].start();
        }

        // 等待所有线程执行完毕
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println("timeOut:" + stopWatch.getTime(TimeUnit.MILLISECONDS));
        System.out.println("atomInt = " + atomInt.get());
        int acquire = atomInt.getAcquire();
        System.out.println("acquire = " + acquire);
        //timeOut:182
        //atomInt = 10000000
        //acquire = 10000000
    }
}
