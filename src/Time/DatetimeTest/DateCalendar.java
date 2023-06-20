package Time.DatetimeTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class DateCalendar {
    @Test
    public void CalendarTest() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar);
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);//当前是这个月的第几天

        //set()
        int d = calendar.get(Calendar.PM);
        System.out.println("d = " + d); //d = 2023

        /**
         * Instant
         */
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);
        //instant = 2023-03-07T13:52:00.275127600Z
        System.out.println("==========");

        System.out.println("year = " + calendar.get(Calendar.YEAR));
        System.out.println("MONTH = " + calendar.get(Calendar.MONTH));
        System.out.println("DATE = " + calendar.get(Calendar.DATE));
        //year = 2023
        //MONTH = 2
        //DATE = 7

        Date time = calendar.getTime();
        System.out.println("Time = " + time);
        // calendar.getTime() = Tue Mar 07 22:00:00 CST 2023
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = format.format(time);
        System.out.println("s = " + s); // s = 2023-03-07 10:01:37

        String s1 = "2077-3-8 10:10:11";
        Date time1 = format.parse(s1);
        calendar.setTime(time1);
        System.out.println("time1 = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(calendar.getTime()));
        //time1 = 2077-03-08 10:10:11 000
    }

    @Test
    public void Calendar_AfterDay() throws ParseException {
        //一周后的日期
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 7);
        Date d = ca.getTime();
        String after = formatDate.format(d);
        System.out.println("一周后日期：" + after);

        //算两个日期间隔多少天，计算间隔多少年，多少月方法类似
        String dates1 = "2021-12-30";
        String dates2 = "2021-12-26";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(dates1);
        Date date2 = format.parse(dates2);
        int day = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        System.out.println(dates1 + "和" + dates2 + "相差" + day + "天");
        //结果：2021-12-23和2021-12-23相差300天

    }



    @Test
    public void t1() {
        //2.一维数字list转为数组
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        int[] arr = new int[3];
        //下一行代码是仿照String类型写的。但是报错'toArray(T[])' in 'java.util.List' cannot be applied to '(int[])'
        //原因：toArray()方法应该传入的参数T是泛型，但是泛型必须是引用类型，不能是基本类型（比如int）
        // arr=list.toArray(new int[0]);

        //解决方法1：采用流式处理Stream进行处理
        int[] arr1 = list2.stream().mapToInt(Integer::valueOf).toArray();
        for (int i : arr1) {
            System.out.println(i);
        }
        /**
         * mapToInt拿到Int流对象，就可以toArray返回int数组了
         *
         * //这里x->x是lambda表达式，对应一个函数式接口，因为集合中泛型为Interger类型，自动拆箱功能，所以直接返回x就行
         * //如果这里式String泛型要转为int数组的话，就要调用Integer.parseInt()方法，lambda写法：mapToInt(Integer::parseInt)
         */
        System.out.println(list2.stream().mapToInt(Integer::valueOf).toArray()); // [I@2ff4f00f
        System.out.println(list2.stream().mapToInt(x-> x).toArray()); // [I@3f0ee7cb


    }


}
