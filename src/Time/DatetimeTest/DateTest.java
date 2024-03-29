package Time.DatetimeTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
 * <p>
 * 日期：yyyy-MM-dd
 * 时间：HH:mm:ss
 * 带毫秒的时间：HH:mm:ss.SSS
 * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
 * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
 */
public class DateTest {




    public static void main(String[] args) throws ParseException {
        /**
         * jdk1.8之前
         */
        Date d1 = new Date();
        System.out.println("date = " + d1); // date = Tue Mar 07 23:09:38 CST 2023
        //date = Thu Sep 07 08:59:09 CST 2023
        Date d2 = new Date();
        System.out.println((d2.getTime() - d1.getTime()) / 1000 / 3600 / 24);
        //0

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ,  kk:mm:ss E");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd ,  hh:mm:ss E");
        String format = simpleDateFormat.format(d1);
        String format1 = simpleDateFormat1.format(d1);
        System.out.println("Date : " + format);
        System.out.println("Date : " + format1);
        //Date : 2023-09-07 ,  08:59:09 周四
        //Date : 2023-09-07 ,  08:59:09 周四

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s1 = "2203-03-07 11:18:57";
        Date parse = format2.parse(s1);
        System.out.println("parse = " + simpleDateFormat.format(parse));
        //parse = 2203-03-07 ,  11:18:57 周一
    }

    @Test
    public void LocalDateTime_test() {
        /**
         * LocalDateTime
         */
        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("time format : %s", time));
        //time format : 09:00:59

        //format yyyy-MM-dd HH:mm:ss
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("dateTimeFormatter = " + dateTimeFormatter); // 可读性差
        System.out.println(String.format("dateTime format : %s", dateTimeFormatter.format(dateTime)));
        //dateTime format : 2023-03-07 11:23:40


        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");
        System.out.println("newFormat.format(dateTime) = " + newFormat.format(dateTime));
        // newFormat.format(dateTime) = 2023-11-16 11:52:01:2978
        /**
         * withNano:
         *      此方法接受单个参数nano，该参数表示要在结果中设置的纳秒(从0到999、999、999)。
         */
        //format HH:mm:ss
        LocalTime time1 = LocalTime.now().withNano(9999999);
        System.out.println(String.format("time format : %s", time1));
        // time format : 21:45:54.009999999

        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS");
        System.out.println(sdf.format(LocalDateTime.now()));
        //2023-09-07 09:00:59:76
        // yyyy-MM-dd HH:mm:ss


        /**
         * 1.8之后的用法
         *
         * Java 8 之前 转换都需要借助 SimpleDateFormat 类，
         * 而Java 8 之后只需要 LocalDate、LocalTime、LocalDateTime的 of 或 parse 方法。
         */

        /**
         * 年-月-日
         */
        LocalDate date = LocalDate.of(2021, 1, 26);
        System.out.println("date = " + date); // date = 2021-01-26

        LocalDate time0 = LocalDate.parse("2021-01-26");
        System.out.println("time = " + time0); // time = 2021-01-26

        /**
         * 年-月-日 时-分-秒
         */
        LocalDateTime dateTime1 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
        System.out.println("dateTime1 = " + dateTime1);
        // dateTime1 = 2021-01-26T12:12:22

        LocalDateTime dateTime2 = LocalDateTime.parse("2021-01-26T12:12:22", DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("dateTime2 = " + dateTime2);
        // dateTime2 = 2021-01-26T12:12:22

        LocalDateTime dateTime3 = LocalDateTime.parse("2021-01-26 12:12:22", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("dateTime3 = " + dateTime3);
        // dateTime3 = 2021-01-26T12:12:22

        /**
         * 时-分-秒
         */
        LocalTime time2 = LocalTime.of(12, 12, 22);
        System.out.println("time2 = " + time2);
        // time2 = 12:12:22

        LocalTime time3 = LocalTime.parse("12:12:22");
        System.out.println("time3 = " + time3);
        // time3 = 12:12:22


        System.out.println(DateTest.GetCurrentTime());
        // 2023-11-16 11:58:32.263

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat ft2 = new SimpleDateFormat ("HHmm");

        System.out.println("当前时间为: " + ft.format(dNow));
        // 当前时间为: 2023-12-07 03:35:37
        System.out.println("当前时间为: " + ft1.format(dNow));
        //当前时间为: 2023-12-07 15:37:30
        System.out.println("当前时间为: " + ft2.format(dNow));
        //当前时间为: 1539

        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MMMM-dd hh:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
        String timeFormat = format.format(dNow);
        String timeFormat1 = format1.format(dNow);
        System.out.println("timeFormat = " + timeFormat);
        //timeFormat = 02023-十二月-31 10:39:23
        System.out.println("timeFormat1 = " + timeFormat1);
        //timeFormat1 = 2023-12月-31 10:39:23
    }


    /**
     * 获取当前时间
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String GetCurrentTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    /**
     * java获取时间差
     */
    @Test
    public void durationTest() {
        LocalTime start = LocalTime.now();
//        LocalTime end = LocalTime.of(20,13,23);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime end = LocalTime.now();
        System.out.println("time: " + Duration.between(start,end).toMillis() + "ms");
        Duration duration = Duration.between(start,end);
        System.out.println("时间间隔为"+duration.toHours()+"h");
        System.out.println("时间间隔为"+duration.toMillis()+"ms");
        System.out.println("时间间隔为"+duration.toNanos()+"ns");
        //time: 2002ms
        //鏃堕棿闂撮殧涓�0h
        //鏃堕棿闂撮殧涓�2002ms
        //鏃堕棿闂撮殧涓�2002155600ns
    }


}
