package Assert;

import org.junit.Test;

public class AssertTest {

    /**
     * 一般只用于测试
     * 表达式的后面添加错误信息：assert a > 10 : "我是自定义的错误信息"; 只有失败才会打印后面的error
     */
    @Test
    public void test1() {
        int a= 1;
        assert a > 0 : "a > 0";
//        assert a < 0 : "a > 0";
        //java.lang.AssertionError: a > 0
        boolean flag = true;
        assert flag == false : "Please check: flag=" + flag;
        System.out.println("flag = " + flag);
    }

    public static void main(String[] args) {
        boolean flag = true;
    }
}
