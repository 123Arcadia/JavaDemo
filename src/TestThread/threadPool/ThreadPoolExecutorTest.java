package TestThread.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {

    /**
     * ThreadPoolExecutor: 继承了ExecutorService(submit) 和 Executors的接口(execute)
     *
     * AbortPolicy(默认) - 抛出异常，中止任务。抛出拒绝执行 RejectedExecutionException 异常信息。线程池默认的拒绝策略。必须处理好抛出的异常，否则会打断当前的执行流程，影响后续的任务执行
     * CallerRunsPolicy - 使用调用线程执行任务。当触发拒绝策略，只要线程池没有关闭的话，则使用调用线程直接运行任务。一般并发比较小，性能要求不高，不允许失败。但是，由于调用者自己运行任务，如果任务提交速度过快，可能导致程序阻塞，性能效率上必然的损失较大
     * DiscardPolicy - 直接丢弃，其他啥都没有
     * DiscardOldestPolicy - 丢弃队列最老任务，添加新任务。当触发拒绝策略，只要线程池没有关闭的话，丢弃阻塞队列 workQueue 中最老的一个任务，并将新任务加入
     *
     * shutdown：线程池将要关闭事件,此方法会等待线程池中正在执行的任务和队列中等待的任务执行完毕再关闭
     * shutdownNow（）：线程池立即关闭事件,此方法会立即关闭线程池,但是会返回队列中等待的任务
     *
     */
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


    public static void main(String[] args) {
        int N = 1500;
        /**
         * future.get方法会阻塞当前主流程，在超时时间内等待子线程返回结果
         */
        Future<Integer> future = MSG_THREAD_POOL.submit(() -> {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += i;
            }
            return sum;
        });
        MSG_THREAD_POOL.shutdown();
        int res = 0;
        try {
            res = future.get(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("future.get() = " + res);
        //future.get() = 1124250
    }
}
