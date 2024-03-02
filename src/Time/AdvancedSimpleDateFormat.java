package Time;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 优化使用
 */
public class AdvancedSimpleDateFormat {
    @Test
    public void test01() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        String dateRes = dateFormat.format(date);
        System.out.println("dateRes = " + dateRes);
    }


    // ---------------------------------
    /**
     * SimpleDateFormat线程不安全， 多线程下必须对其加锁 或者 使用ThreadLocal
     */
    private static ThreadLocal<DateFormat> threadLocal=new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    @Test
    public void testThreadLocal() {
//        通过ThreadLocal来存放SimpleDateFormat
        Date date = new Date();
        AdvancedSimpleDateFormat thisClass = new AdvancedSimpleDateFormat();
        String res = thisClass.format(date);
        System.out.println("res = " + res);
    }

    // ---------------------------------

    /**
     * 第三方包:cn.hutool和common-lang3
     * 1. DateFormatUtils
     * 2. FastDateFormat:
     *      通常会建议将 FastDateFormat 实例设置为静态变量，避免频繁创建。因为它的内部实现是线程安全的，所以可以在多个线程间共享使用。
     */
    @Test
    public void test02() {
        Date date = new Date();
        String dateRes = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("dateRes = " + dateRes);

        // 创建一个 FastDateFormat 实例，指定日期格式
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

        // 获取当前时间并格式化
        Date now = new Date();
        String formattedDate = fdf.format(now);

        System.out.println("formattedDate = " + formattedDate);
    }

    /**
     *
     */


    /**
     * Java8的DateTimeFormatter也是线程安全的，而SimpleDateFormat并不是线程安全
     */
    @Test
    public void test03() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS a");
        String nowStr = now .format(format);
        System.out.println("nowStr = " + nowStr);
    }
}
