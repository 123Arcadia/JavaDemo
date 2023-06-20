package EnumTest;

public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;

        System.out.println(summer);
        System.out.println(Season1.class.getSuperclass()); //class java.lang.Enum
        System.out.println(summer.getClass());
        System.out.println(Season1.class);
        //Season1{seasonName='夏天', seasonDesc='烈日炎炎'}
        //class java.lang.Enum
        //class EnumTest.Season1
        //class EnumTest.Season1
        System.out.println("========");
        System.out.println(Season1.valueOf("SUMMER"));
        System.out.println(Season1.valueOf("WINTER"));
        //Season1{seasonName='夏天', seasonDesc='烈日炎炎'}
        //[LEnumTest.Season1;@15aeb7ab
        /**
         * values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
         */
        System.out.println(Season1.values());
        for (Season1 value : Season1.values()) {
            System.out.println(value);
            //Season1{seasonName='春天', seasonDesc='春暖花开'}
            //Season1{seasonName='夏天', seasonDesc='烈日炎炎'}
            //Season1{seasonName='秋天', seasonDesc='秋高气爽'}
            //Season1{seasonName='冬天', seasonDesc='冰天雪地'}
        }


    }
}

//使用enum关键字定义枚举类
enum Season1 {
    //1. 先显示列出枚举类的对象，以","分隔，";"结尾
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "烈日炎炎"),
    ANTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");
    //2. 声明Season对象的属性：private final修饰
    private final String seasonName;
    private final String seasonDesc;
    //3. 私有化类的构造器，并给对象赋值
    Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //4. 其他诉求：获取枚举类的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //5. 其他诉求：提供toString()
    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}