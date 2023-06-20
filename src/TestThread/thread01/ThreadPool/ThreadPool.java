package TestThread.thread01.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

        service.shutdown();
    }
}
