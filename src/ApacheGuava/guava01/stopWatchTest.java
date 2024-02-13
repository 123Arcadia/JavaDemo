package ApacheGuava.guava01;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

public class stopWatchTest {

    /**
     * <strong>Stopwatch</strong> : ��{@link guava}��{@link Spring.framewrk.core} �� {@link apache.commons.lang3}
     *
     *
     * Stopwatch createStarted()����������������һ���µ����ʹ�� System#nanoTime ����Ϊ��ʱ��Դ��
     * Stopwatch createUnstarted()������������������һ���µ����ʹ�� System#nanoTime ����Ϊ��ʱ��Դ��
     * long elapsed(TimeUnit desiredUnit)�����ش��������ʾ�ĵ�ǰ����ʱ�䣬�������ʱ�䵥λ��ʾ���κη�����������(�����Ŀ������ܳ���һ΢�룬���TimeUnit.NANOSECONDS������ָ������ͨ��û����)
     * boolean isRunning()��������ڴ�����ϵ���start����}���������ϴε���start��������δ����stop�������򷵻�true
     * Stopwatch reset()��������������ʱ������Ϊ�㣬����������ֹͣ״̬��
     * Stopwatch start()���������,�������Ѿ������У��� IllegalStateException
     * Stopwatch stop()��ֹͣ��������Ķ�ȡ�����ص�ĿǰΪֹ�����Ĺ̶�����ʱ�䡣
     * tring toString()�����ص�ǰ����ʱ����ַ�����ʾ��ʽ������ 2.588 s��106.8 ms
     */
    @Test
    public void testStopwatch() throws InterruptedException {
        SecureRandom secureRandom = new SecureRandom();
        Stopwatch stopwatch = Stopwatch.createStarted();

        int nextInt = secureRandom.nextInt(2000);
        System.out.println("����1Ԥ���ʱ��" + nextInt);//����1Ԥ���ʱ��81
        TimeUnit.MILLISECONDS.sleep(nextInt);
        //elapsed����: �����Ŀ������ܳ���һ΢�룬���TimeUnit.NANOSECONDS������ָ������ͨ��û���á�
        System.out.println("\t����1ʵ�ʺ�ʱ��" + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "(����)");// ����1ʵ�ʺ�ʱ��563(����)
        //����1Ԥ���ʱ��938
        //	����1ʵ�ʺ�ʱ��1001(����)

        stopwatch.reset().start();
        nextInt = secureRandom.nextInt(4000);
        System.out.println("����2Ԥ���ʱ��" + nextInt);//����2Ԥ���ʱ��1591
        TimeUnit.MILLISECONDS.sleep(nextInt);
        System.out.println("\t����2ʵ�ʺ�ʱ��" + stopwatch.toString());// ����2ʵ�ʺ�ʱ��1.592 s
        //����2Ԥ���ʱ��2336
        //	����2ʵ�ʺ�ʱ��2.337 s

        stopwatch.reset().start();
        nextInt = secureRandom.nextInt(3000);
        System.out.println("����3Ԥ�ƺ�ʱ��" + nextInt);//����3Ԥ�ƺ�ʱ��1964
        TimeUnit.MILLISECONDS.sleep(nextInt);
        System.out.println("\t����3ʵ�ʺ�ʱ��" + stopwatch.stop().toString());// ����3ʵ�ʺ�ʱ��1.965 s
        //����3Ԥ�ƺ�ʱ��2354
        //	����3ʵ�ʺ�ʱ��2.355 s
    }
}
