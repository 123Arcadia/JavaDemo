package AAATemp;

import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class temp {

    @Test
    public void test01() {
        Integer x = 2;
        Long y = 2L;
        Long z = 2L;
        System.out.println(x.equals(y)); // false先判断类型
        System.out.println(x.intValue() == y.intValue()); // true

        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 1) { //如果是基数
                y += 1;
            } else {
                z += 2;
            }
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
        System.out.println("paramlist = " + Arrays.stream(paramlist).toList());
        System.out.println(Arrays.toString(paramlist));

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
}

@Data
@ToString
class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}