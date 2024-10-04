package AAATemp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.*;

public class temp {

    @Test
    public void test02() {
        //随机生成yyyy/M/dd格式的日志
        Random near = new Random(2000);
        Random month = new Random(10);
        Random day = new Random(10);

        for (int i = 0; i < 10; i++) {

            String date = String.valueOf(near.nextInt(2010, 2026) + "/" +
                    month.nextInt(1, 13) + "/" +
                    day.nextInt(1, 32));
            System.out.println(date);
        }



    }


    @Test
    public void booleanTest() {
        int c = 12;
        boolean flag = true;
        if (Boolean.TRUE == flag) {
            System.out.println("true");
        }
        if (Boolean.TRUE.equals(flag)) {
            System.out.println("true2");
        }
    }

    @Test
    public void test() {
        String s = "zcw";
        Map<String, Integer> map = new HashMap<>();
        Person person = new Person("zzz", 19);
        map.put("zzz", 20);

        map.put(person.getName(), person.getAge());
        System.out.println(map); //{zzz=19}
        System.out.println(map.get(person.getName())); // 19
        System.out.println(map.get("zzz")); // 19


    }

    @Test
    public void getUserDir() throws Exception {
        System.out.println(System.getProperty("user.dir")); //D:\javaProject\javaTesting
        System.out.println((double) 7 / 2);
        System.out.println((double) 135 / 2);
        System.out.println(getMethodName("zcw"));


    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的、
     */
    private String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        System.out.println("items = " + items);
        System.out.println("(char) items[0] = " + (char) items[0]);
        return new String(items);
    }

    @Test
    public void split() {
        String num = "[0.2][0.2][0.2]";
        String param = num.substring(1, num.length() - 1);
        String[] paramlist = param.split("]\\[");
        System.out.println("paramlist = " + Arrays.toString(paramlist));
        System.out.println(Arrays.toString(paramlist));

        Object o = new Object();

//        Map<Integer, JSONArray> StorageParamSet = new HashMap<Integer, JSONArray>();
//        for (int i = 0; i < paramlist.length; i++) {
//            JSONArray paramListArray = StorageParamSet.get(i+1);
//            for (Object o : paramListArray) {
//                System.out.println(o);
////                JSONObject json = (JSONObject) o;
////                String param = json.getString("PARAMETERCODE");
////                if (param.equals(params.getPARAMETERCODE())) {
////                    json.put("PARAVALUES", paramlist[i]);
////                }
//            }
//        }


    }

    /**
     * 把一个字符串的第一个字母大写、效率是最高的
     */
    @Test
    public void test01() {
        String res = getMethodName("zcw");
    }

    @Test
    public void printStringBuider() {
        List<String> list = new ArrayList<>(Arrays.asList("zzcw", "123", "4532"));
        for (String s : list) {
            switch (s) {
                case "zzcw":
                    System.out.println("1 = " + s);
                    break;
                case "123":
                    System.out.println("2 = " + s);
                    break;
                case "4532":
                    System.out.println("3 = " + s);
                    break;
                default:
                    break;
            }
            System.out.print("=_");
        }

    }



}

//@Data
@ToString
class Person extends Teach {
    private String name;
    private Integer age;

    public Integer newAge = 1;


    public Person(String name, Integer age) {
        super(name, age);
    }
}

@Data
@ToString
@AllArgsConstructor
class Teach {
    private String name;
    public Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}