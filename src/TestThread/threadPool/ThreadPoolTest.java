package TestThread.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    public static ExecutorService MSG_THREAD_POOL = new ThreadPoolExecutor(3, 3, 30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5),
            Executors.defaultThreadFactory(),
//            new ThreadPoolExecutor.DiscardPolicy()
//            new ThreadPoolExecutor.AbortPolicy() // 拒绝:抛出异常
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );


    @Test
    public void outMaxNUm() {
        for (int i = 0; i < 10; i++) {
            MSG_THREAD_POOL.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("run" + Thread.currentThread().getId() + ", " + Thread.currentThread().getName());
                }
            });
        }

        System.out.println("task finished!");
    }
}
