package TestThread.thread01.ThreadPool;

import org.junit.Test;

import java.util.concurrent.*;

class Number implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " :i = " + i);
        }

    }
}
class Number1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if(i % 2 == 1)
            System.out.println(Thread.currentThread().getName() + " :i = " + i);
        }

    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //提供制定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //设置线程池属性
        ThreadPoolExecutor service1 = (ThreadPoolExecutor)service;
        service1.setCorePoolSize(15);
        service1.setMaximumPoolSize(15);

        service.execute(new Number());
        service.execute(new Number1());

//        service.shutdown();

        /**
         * 或者
         */
//        ExecutorService DEVICE_MSG_THREAD_POOL = new ThreadPoolExecutor(30, 30, 30, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(100));
    }

    @Test
    public void executorService() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("----------------");
                System.out.println(Thread.currentThread().getName() + "====" + Thread.currentThread().getId());
                //pool-1-thread-1-24
            }
        });
//        service.execute(() -> {
//            System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId());
//
//        });
        Thread.sleep(1000); // 防止程序执行完，thread还没有完便强制结束
        service.shutdown();
    }
}
