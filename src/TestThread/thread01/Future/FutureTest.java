package TestThread.thread01.Future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * Callable
 * 1.call()可以有返回值的。
 * *      2.call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * *      3.Callable是支持泛型的
 * *      4.需要借助FutureTask类，比如获取返回结果
 */

/**
 * FutrueTask是Futrue接口的唯一的实现类
 */
public class FutureTest {

    @Test
    public void testFuture() {
        CompletableFuture<Integer> futSum = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        });
        CompletableFuture<Integer> thenApply = futSum.thenApply((s) -> {
            return ++s;
        });
        try {
            System.out.println("futSum=" + futSum.get() + ", thenApply=" + thenApply.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        String username = "name";
        String password = "123";
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            future.complete("rtsp://" + username + ":" + password + "@" + Inet4Address.getLocalHost());
            String res = future.get();//get是获取future中保存的字符串
            System.out.println("res = " + res);
            //futSum=4950, thenApply=4951
            //res = rtsp://name:123@LAPTOP-89JUTCMV/192.168.0.104
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            return 1;
        });

        CompletableFuture<Integer> cf2 = cf1.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread() + " cf2 do something....");
            result += 2;
            return result;
        });
        //等待任务1执行完成
        System.out.println("cf1:->" + cf1.get());
        //等待任务2执行完成
        System.out.println("cf2:->" + cf2.get());
        //Thread[ForkJoinPool.commonPool-worker-1,5,main] cf1 do something....
        //Thread[ForkJoinPool.commonPool-worker-1,5,main] cf2 do something....
        //cf1:->1
        //cf2:->3
    }


    // whenComplete和whenCompleteAsync
    @Test
    public void whenComplete() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            int a = 1 / 0;
            return 1;
        });

        CompletableFuture<Integer> cf2 = cf1.whenComplete((result, e) -> {
            System.out.println("上个任务结果：" + result);
            System.out.println("上个任务抛出异常：" + e);
            System.out.println(Thread.currentThread() + " cf2 do something....");
        });

//        //等待任务1执行完成
//        System.out.println("cf1结果->" + cf1.get());
//        //等待任务2执行完成
        try {
            System.out.println("cf2结果->" + cf2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("cf1 = " + cf1);
    }

    /**
     * 当可用处理器>1就使用ForkJoinPool,否则新开Thread()
     */
    @Test
    public void test_runAsync() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("runAsync!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
//        LocalTime start = LocalTime.now();
        LocalDateTime start = LocalDateTime.now();
        future.join();
        System.out.println(future.join());
//        LocalTime end = LocalTime.now();
        LocalDateTime end = LocalDateTime.now();
        System.out.println("耗时:" + Duration.between(start, end).toMillis() + "ms");
        //鑰楁椂:3002ms
        System.out.println(ForkJoinPool.getCommonPoolParallelism()); // 19 (>1则是ForkJointPool)
        String pp = System.getProperty
                ("java.util.concurrent.ForkJoinPool.common.parallelism");
        System.out.println("pp = " + pp);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    /**
     * - **join()**： - 不会抛出受检异常（Checked Exception），而是将任何由异步计算过程中产生的异常转换为未检查异常（Unchecked Exception），通常是 CompletionException。
     *      - 使用时无需捕获或声明可能抛出的异常类型，简化了代码编写。
     * - **get()**： - 如果异步计算抛出了异常，那么 get() 方法将会直接抛出这个异常，包括受检异常和运行时异常。(使用try-catch处理)
     *
     * 注意: 两者都会**阻塞**调用线程直到异步计算完成。
     */
    @Test
    public void te() {
        String str = "K01，K02";
        String[] split = str.split(",");
        System.out.println(Arrays.toString(split) + ", len=" + split.length + ", " + split[0]);

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            try {
                Thread.sleep(800);
                // 分别修改为5000、800，查看结果
                // 800: 会get()到新值
                // 5000: 会超时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  ++sum;
        }, threadPool);

        StopWatch sw = StopWatch.create();
        Collection<Thread> allThreads = ThreadUtils.getAllThreads();
        // 获取main、pool中的线程List
        List<Thread> listThread = getNeedThreadFromAllPoll(allThreads);

        System.out.println("[get before]listThread = " + getInfoInThread(listThread));
        //[get before]listThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;
        // 如果是得到gte摸鱼超时: [get After]needThread = main, RUNNABLE, true;pool-1-thread-1, WAITING, true;
        int res = 0;
        try {

            Thread main = Thread.currentThread();

            System.out.println(main.getName() + ", " + main.getState()); // main, RUNNABLE
            sw.start();
            res = future.get(1000, TimeUnit.MILLISECONDS);
            sw.stop();

            System.out.println(main.getName() + ", []" + main.getState());
        } catch (Exception e) {
            System.out.println("Exceptions: "+ExceptionUtils.getStackTrace(e));;
            if (!future.isDone()) {
                System.out.println("it is not Finished!"); // 如果超时，会执行到这里
            }
        }

        Collection<Thread> allThreadsAfter = ThreadUtils.getAllThreads();
        List<Thread> needThread = getNeedThreadFromAllPoll(allThreadsAfter);
        System.out.println("[get After]needThread = " + getInfoInThread(needThread));
        //[get After]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;

        // 打印:thread
        //"Thread[" + getName() + "," + getPriority() + "," + group.getName() + "]";
        //Thread[main,5,main], Thread[pool-1-thread-1,5,main]
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("res = " + res + ", time: " + sw.getTime());


    }

    /**
     *  获取main、pool中的线程List
     * @param allThreads
     * @return
     */
    private List<Thread> getNeedThreadFromAllPoll(Collection<Thread> allThreads) {
        List<Thread> listThread = new ArrayList<>(2);
        for (Thread thread : allThreads) {
            if (thread.getName().contains("main") || thread.getName().contains("pool"))  {
                listThread.add(thread);
            }
        }
        return listThread;
    }

    public String getInfoInThread(List<Thread> listThread) {
        StringBuilder sb = new StringBuilder();
        listThread.forEach(thread -> {
            sb.append(thread.getName()).append(", ").append(thread.getState()).append(", ").append(thread.isAlive()).append(";");
        });
        return sb.toString();
    }

    @Test
    public void test_FutrueTimeOut() {
        StringBuilder timeInfo = new StringBuilder();

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Collection<Thread> allThreadsAfter = ThreadUtils.getAllThreads();
        List<Thread> needThread = getNeedThreadFromAllPoll(allThreadsAfter);
        System.out.println("[1]needThread = " + getInfoInThread(needThread));
        //[1]needThread = main, RUNNABLE, true;
        StopWatch sw = StopWatch.createStarted();
        long s = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum++;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }, threadPool);
        Integer res = -1;
        try {
            Collection<Thread> allThreadsAfter1_1 = ThreadUtils.getAllThreads();
            List<Thread> needThread1_1 = getNeedThreadFromAllPoll(allThreadsAfter1_1);
            System.out.println("[1.1]needThread = " + getInfoInThread(needThread1_1));
            //超时: [1.1]needThread = main, RUNNABLE, true;pool-1-thread-1, RUNNABLE, true;  为什么main不是阻塞状态?
            res = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("Exceptions: " + ExceptionUtils.getStackTrace(e));
            Collection<Thread> allThreadsAfter2 = ThreadUtils.getAllThreads();
            List<Thread> needThread2 = getNeedThreadFromAllPoll(allThreadsAfter2);
            System.out.println("[2]needThread = " + getInfoInThread(needThread2));
        }
        sw.stop();
        timeInfo.append("funtrue耗时:").append(sw.getTime()).append(", ");
        sw.reset();
        Collection<Thread> allThreadsAfter3 = ThreadUtils.getAllThreads();
        List<Thread> needThread3 = getNeedThreadFromAllPoll(allThreadsAfter3);
        System.out.println("[3]needThread = " + getInfoInThread(needThread3));

        System.out.println("res = " + res);

        //测试: 超时
        //[1]needThread = main, RUNNABLE, true;
        //Exceptions: java.util.concurrent.TimeoutException...
        //[2]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;
        //[3]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;
        //res = -1

        //测试: 不超时
        //[1]needThread = main, RUNNABLE, true;
        //[3]needThread = main, RUNNABLE, true;pool-1-thread-1, RUNNABLE, true;
        //res = 10

        // 超时->执行线程state: TIMED_WAITING
        sw.start();
        threadPool.submit(() -> {
            try {
                Thread.sleep(1000);
                long e = System.currentTimeMillis();
                Collection<Thread> allThreadsAfter4 = ThreadUtils.getAllThreads();
                List<Thread> needThread4 = getNeedThreadFromAllPoll(allThreadsAfter4);
                System.out.println("[4]needThread = " + getInfoInThread(needThread4));
                System.out.println("The Second Thread is Finished! time:" + (e - s));
                //超时:
                //[4]needThread = main, TIMED_WAITING, true;pool-1-thread-1, RUNNABLE, true;
                //The Second Thread is Finished! time:6022
                // 解释: 这里是6022，意味着先把Futrue的5s任务执行完，在执行第二个任务1s, 共6s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sw.stop();
        timeInfo.append("second thread耗时:").append(sw.getTime()).append(", ");
        sw.reset();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collection<Thread> allThreadsAfter5 = ThreadUtils.getAllThreads();
        List<Thread> needThread5 = getNeedThreadFromAllPoll(allThreadsAfter5);
        System.out.println("[5]needThread = " + getInfoInThread(needThread5));
        // 超时->[5]needThread = main, RUNNABLE, true;pool-1-thread-1, WAITING, true;

        System.out.println(timeInfo.toString());

    }

    /**
     * 测试超时会，执行线程直接中断（去执行下一个任务）
     */
    @Test
    public void test_FutrueTimeOut2() {
        StringBuilder timeInfo = new StringBuilder();

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Collection<Thread> allThreadsAfter = ThreadUtils.getAllThreads();
        List<Thread> needThread = getNeedThreadFromAllPoll(allThreadsAfter);
        System.out.println("[1]needThread = " + getInfoInThread(needThread));
        //[1]needThread = main, RUNNABLE, true;
        StopWatch sw = StopWatch.createStarted();
        long s = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum++;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }, threadPool);
        Integer res = -1;
        try {
            Collection<Thread> allThreadsAfter1_1 = ThreadUtils.getAllThreads();
            List<Thread> needThread1_1 = getNeedThreadFromAllPoll(allThreadsAfter1_1);
            System.out.println("[1.1]needThread = " + getInfoInThread(needThread1_1));
            //超时: [1.1]needThread = main, RUNNABLE, true;pool-1-thread-1, RUNNABLE, true;  为什么main不是阻塞状态?
            res = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("Exceptions: " + ExceptionUtils.getStackTrace(e));
//            threadPool.shutdownNow();
            Collection<Thread> allThreadsAfter2 = ThreadUtils.getAllThreads();
            List<Thread> needThread2 = getNeedThreadFromAllPoll(allThreadsAfter2);
            System.out.println("[2]needThread = " + getInfoInThread(needThread2));
        }
        sw.stop();
        timeInfo.append("funtrue耗时:").append(sw.getTime()).append(", ");
        sw.reset();
        Collection<Thread> allThreadsAfter3 = ThreadUtils.getAllThreads();
        List<Thread> needThread3 = getNeedThreadFromAllPoll(allThreadsAfter3);
        System.out.println("[3]needThread = " + getInfoInThread(needThread3));

        System.out.println("res = " + res);

        //测试: 超时
        //[1]needThread = main, RUNNABLE, true;
        //Exceptions: java.util.concurrent.TimeoutException...
        //[2]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;
        //[3]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;
        //res = -1

        //测试: 不超时
        //[1]needThread = main, RUNNABLE, true;
        //[3]needThread = main, RUNNABLE, true;pool-1-thread-1, RUNNABLE, true;
        //res = 10

        // 超时->执行线程state: TIMED_WAITING
        sw.start();
        threadPool.submit(() -> {
            try {
                Thread.sleep(1000);
                long e = System.currentTimeMillis();
                Collection<Thread> allThreadsAfter4 = ThreadUtils.getAllThreads();
                List<Thread> needThread4 = getNeedThreadFromAllPoll(allThreadsAfter4);
                System.out.println("[4]needThread = " + getInfoInThread(needThread4));
                System.out.println("The Second Thread is Finished! time:" + (e - s));
                //超时:
                //[4]needThread = main, TIMED_WAITING, true;pool-1-thread-1, RUNNABLE, true;
                //The Second Thread is Finished! time:6022
                // 解释: 这里是6022，意味着先把Futrue的5s任务执行完，在执行第二个任务1s, 共6s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        sw.stop();
        timeInfo.append("second thread耗时:").append(sw.getTime()).append(", ");
        sw.reset();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collection<Thread> allThreadsAfter5 = ThreadUtils.getAllThreads();
        List<Thread> needThread5 = getNeedThreadFromAllPoll(allThreadsAfter5);
        System.out.println("[5]needThread = " + getInfoInThread(needThread5));
        // 超时->[5]needThread = main, RUNNABLE, true;pool-1-thread-1, WAITING, true;

        System.out.println(timeInfo.toString());
    }


    /**
     * 测试超时会，执行线程直接中断（去执行下一个任务） Future.cancel(true) 和 shutdownNow()后Thread的状态变化
     */
    @Test
    public void test_ThreasStateAfterInterrupt() {
        StringBuilder timeInfo = new StringBuilder();

        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        Collection<Thread> allThreadsAfter = ThreadUtils.getAllThreads();
        List<Thread> needThread = getNeedThreadFromAllPoll(allThreadsAfter);
        System.out.println("[1]needThread = " + getInfoInThread(needThread));
        //[1]needThread = main, RUNNABLE, true;
        StopWatch sw = StopWatch.createStarted();
        long s = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum++;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }, threadPool);
        Integer res = -1;
        try {
            Collection<Thread> allThreadsAfter1_1 = ThreadUtils.getAllThreads();
            List<Thread> needThread1_1 = getNeedThreadFromAllPoll(allThreadsAfter1_1);
            System.out.println("[1.1]needThread = " + getInfoInThread(needThread1_1));
            //超时: [1.1]needThread = main, RUNNABLE, true;pool-1-thread-1, RUNNABLE, true;  为什么main不是阻塞状态?
            res = future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("Exceptions: " + ExceptionUtils.getStackTrace(e));
            /**
             * 如果设置为 true，将会尝试中断执行中的任务，但这并不能保证一定能中断成功，因为任务本身可能不响应中断请求或部分资源释放不能立即完成。
             * 否则为false仅在任务尚未开始或已完成时取消。
             */
            future.cancel(true);
            Collection<Thread> allThreadsAfter2 = ThreadUtils.getAllThreads();
            List<Thread> needThread2 = getNeedThreadFromAllPoll(allThreadsAfter2);
            System.out.println("[2]needThread = " + getInfoInThread(needThread2) + ", isCancel:" + future.isCancelled());
            //[2]needThread = main, RUNNABLE, true;pool-1-thread-1, TIMED_WAITING, true;, isCancel:true
        }
        sw.stop();
        timeInfo.append("funtrue耗时:").append(sw.getTime()).append(", ");
        sw.reset();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Collection<Thread> allThreadsAfter3 = ThreadUtils.getAllThreads();
        List<Thread> needThread3 = getNeedThreadFromAllPoll(allThreadsAfter3);
        System.out.println("[3]needThread = " + getInfoInThread(needThread3));
        //[3]needThread = main, RUNNABLE, true;pool-1-thread-1, WAITING, true;
        long e1 = System.currentTimeMillis();
        System.out.println("res = " + res + ", timeInfo:" + timeInfo);
        System.out.println("date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        sw.start();
        threadPool.execute(() -> {
            System.out.println("running Finally");
        });
        sw.stop();
        System.out.println("time: " + sw.getTime() + ", e1-s = " + (e1-s));
        //date: 2024-02-21 01:48:59.016
        //running Finally
        //time: 0, e1-s = 7022 （1000ms后判断超时，任务取消，继续执行6000ms, 共7000ms+）

    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Sum implements Runnable {
    private int N = 100;

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum++;
        }
    }
}
