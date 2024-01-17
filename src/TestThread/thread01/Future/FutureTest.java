package TestThread.thread01.Future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Callable
 * 1.call()可以有返回值的。
 * *      2.call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * *      3.Callable是支持泛型的
 * *      4.需要借助FutureTask类，比如获取返回结果
 */

/**
 * FutrueTask是Futrue接口的唯一的实现类
 *
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
            System.out.println("" + futSum.get() + ", thenApply=" + thenApply.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
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
            int a = 1/0;
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
