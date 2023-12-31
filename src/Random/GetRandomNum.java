package Random;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <a href="https://blog.csdn.net/qq_33204709/article/details/129229618?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170081799816800184199967%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170081799816800184199967&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduend~default-2-129229618-null-null.nonecase&utm_term=ThreadLocalRandom&spm=1018.2226.3001.4450">Random讲解</a>
 * <h3>高并发使用 Random 或 SecureRandom 会变慢。</h3>
 */
public class GetRandomNum {
    /**
     * 生成一个[0,n)的随机数
     * @param n
     * @return
     */
    public static int getRandomNum(int n) {
        return (int) (Math.random() * n);
    }

    /**
     * 生成3到10的随机数
     * @return
     */
    public static int getRandomNum2() {
        return (int) (Math.random() * 8 + 3);
    }

    @Test
    public void ThreadLocalRandomTest() {
        System.out.println(getRandomNum(10));
        //0
        System.out.println(getRandomNum2());
        //5
        int num1 = ThreadLocalRandom.current().nextInt(5, 10);
        System.out.println("num1 = " + num1);
        //num1 = 7

    }
}
