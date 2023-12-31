package TestThread.thread01.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 *  1.call()可以有返回值的。
 *  *      2.call()可以抛出异常，被外面的操作捕获，获取异常的信息
 *  *      3.Callable是支持泛型的
 *  *      4.需要借助FutureTask类，比如获取返回结果
 */
class NumThread implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {

            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
        //创建callable接口实现类的对象
        NumThread numThread = new NumThread();
        //将Callable接口实现类的对象作为传递到FutureTask构造器中，
        FutureTask futureTask = new FutureTask(numThread);

        new Thread(futureTask).start();
        //get返回值是FutureTask构造器参数Callable实现重写的call()的返回值
        try {
            Object sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}