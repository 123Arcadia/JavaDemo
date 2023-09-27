package StringTest.StringsUtil;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelToUnderscore {

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


    /**
     * 使用upper华为大写
     */
    @Test
    public void upper() {
        String str = "zcw";
        String upperCase = str.toUpperCase();
        System.out.println("upperCase = " + upperCase);

        char c = 'a';
        System.out.println(c - 32); // 65

        byte[] bt = {95, 96, 97};
        System.out.println(new String(bt)); // "_ ` a"
        System.out.println(String.valueOf((byte) (c - 32))); // 65

        System.out.println(0x0f0);
        //240
        System.out.println(0x0f);
        //15
        System.out.println(0x0f000000);
    }

    /**
     * 小写换成大写
     */
    @Test
    public void change() {
        String fieldName = "zcw_hc_test";
        boolean flag = true;
        byte[] items = fieldName.getBytes();
        for (int i = 0; i < items.length; i++) {
            if (flag) {
                if (items[i] > 96 && items[i] < 123) { // 字母
                    items[i] = (byte) (items[i] - 32);
                }
                flag = false;
            } else if (items[i] == 95) {
                flag = true;
            }
        }
        fieldName = (new String(items)).replaceAll("_", "");

        System.out.println("fieldName = " + fieldName);
        //fieldName = ZcwHcTest
    }

    /**
     * 将字符串转换为十六进制字符串(csdn)
     */
    @Test
    public void str2Hex() {
        String str = "a";
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;

            sb.append(chars[bit]);
            // sb.append(' ');
        }
        System.out.println(Integer.toHexString(12322)); // 3022
        System.out.println("sb = " + sb.toString());
        /** a = 97, 6*16 + 1 = 97 */        // sb = 61


        // 方法：
        byte[] bytes = str.getBytes();
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        System.out.println("hex = " + hex);
        // hex = 61
        System.out.println(Integer.toHexString(1)); // 1

    }


    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        hexStr = hexStr.replace(" ", "");
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }
}
