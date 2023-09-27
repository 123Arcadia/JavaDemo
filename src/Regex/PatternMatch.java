package Regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatch {
    @Test
    public void testMatch() {
        String s = "牛奶:89.8元，香肠:12.9元，啤酒:69元，巧克力:132元";
        String regex = "[0123456789.]+";//【代码1】//匹配数字与小数点的正则表达式的字符串
        int number = 0;
        double sum = 0.0;
        Pattern p = Pattern.compile(regex);//返回正则表达式表达的字符串
        //【代码2】//使用regex初始化模式对象pattern
        Matcher m = p.matcher(s);//正则与s匹配，只有匹配了才返回
        // Pattern.matcher(CharSequenceinput)返回一个Matcher对象。
        // Matcher类的构造方法也是私有的，不能随意创建，只能通过Pattern.matcher(CharSequence input)方法得到该类的实例。
        //Matcher类提供了对正则表达式的分组支持，以及对正则表达式的多次匹配支持。

        System.out.println("m = " + m);

        System.out.println("m.matches() = " + m.matches()); // false
        while (m.find()) {//对字符串s进行匹配
            System.out.println(m.group());//返回匹配到的子字符串
            number = number + 1;
            try {
                sum = sum + Double.parseDouble(m.group());//【代码3】//获取子匹配成功结果字符串并转成Double型类
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("购物小票中的商品种类：" + number + "种");
        System.out.println("购物小票中的价格总额：" + sum + "元");
    }

    /**
     * 使用正则： 小写 -> 大写
     */
    @Test
    public void test01() {
        String name = "zcw_name";
        if (name.contains("_")) {


            Pattern pattern = Pattern.compile("([A-Za-z\\\\d]+)(_)?");
            name = name.toLowerCase();
            Matcher matcher = pattern.matcher(name);

            System.out.println(matcher);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {

                System.out.println("matcher.group() = " + matcher.group());
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
                System.out.println(matcher.group(1));
                System.out.println(matcher.groupCount());
            }
            matcher.appendTail(sb);
            System.out.println("sb.toString() = " + sb.toString()); // sb.toString() = ZCWNAME
        }
    }
}
