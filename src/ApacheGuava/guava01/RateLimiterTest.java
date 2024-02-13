package ApacheGuava.guava01;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter ������/������
 *
 * final RateLimiter rateLimiter = RateLimiter.create(2.0); // rate is "2 permits per second"
 *     void submitTasks(List<Runnable> tasks, Executor executor) {
 *         for (Runnable task : tasks) {
 *             rateLimiter.acquire(); // may wait
 *             executor.execute(task);
 *         }
 *     }
 */
public class RateLimiterTest {


    /**
     * һ���������ź���Semaphore�����ǿ��Ե�������
     */
    @Test
    public void testRateLimiter1() {
        RateLimiter rateLimiter = RateLimiter.create(1);

        Instant start = Instant.now();
        for (int i = 0, max = 10; i < max; i++) {
            int finalI = i;
            new Thread(() -> {
                // boolean tryAcquire = rateLimiter.tryAcquire();
                Thread.currentThread().setName("�߳�" + finalI);
                String threadName = Thread.currentThread().getName();
                try {
                    boolean tryAcquire = rateLimiter.tryAcquire(Duration.ofSeconds(2));
                    if (tryAcquire) {
                        System.out.println(finalI +", ������֤��ִ�����񣬵�ǰ���ʣ�" + rateLimiter.getRate() + "/s ------" + threadName);
                    } else {
                        System.out.println(finalI +", δ������֤���޷�ִ�����񣬵�ǰ���ʣ�" + rateLimiter.getRate() + "/s -------" + threadName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant end = Instant.now();
        System.out.println("��ʱ:" + Duration.between(start, end).toMillis()  + "ms");
    }


    @Test
    public void testRateLimiter2() {
        RateLimiter rateLimiter = RateLimiter.create(1);
        for (int i = 0, max = 10; i < max; i++) {
            int finalI = i;
            new Thread(() -> {
                double acquire = rateLimiter.acquire();
                System.out.println(finalI + ", ������֤��ִ�����񣬵�ǰ���ʣ�"+rateLimiter.getRate()+"/s �ȴ�ʱ��: "+acquire);
            }).start();
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 2023-11-04 16:57:34.480 ==> [Thread-1] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 0.0
        // 2023-11-04 16:57:35.417 ==> [Thread-2] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 0.193155
        // 2023-11-04 16:57:36.417 ==> [Thread-3] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 0.609643
        // 2023-11-04 16:57:37.416 ==> [Thread-4] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 1.251649
        // 2023-11-04 16:57:38.415 ==> [Thread-5] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 1.908182
        // 2023-11-04 16:57:39.416 ==> [Thread-6] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 2.365081
        // 2023-11-04 16:57:40.416 ==> [Thread-7] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 3.319103
        // 2023-11-04 16:57:41.416 ==> [Thread-8] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 3.349201
        // 2023-11-04 16:57:42.416 ==> [Thread-9] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 3.391127
        // 2023-11-04 16:57:43.417 ==> [Thread-10] ==> INFO  com.wmx.guava.RateLimiterTest - ������֤��ִ�����񣬵�ǰ���ʣ�1.0/s �ȴ�ʱ�� 3.91561

    }

}
