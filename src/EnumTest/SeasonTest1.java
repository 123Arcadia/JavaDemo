package EnumTest;

public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;

        System.out.println(summer);
        System.out.println(Season1.class.getSuperclass()); //class java.lang.Enum
        System.out.println(summer.getClass());
        System.out.println(Season1.class);
        //Season1{seasonName='����', seasonDesc='��������'}
        //class java.lang.Enum
        //class EnumTest.Season1
        //class EnumTest.Season1
        System.out.println("========");
        System.out.println(Season1.valueOf("SUMMER"));
        System.out.println(Season1.valueOf("WINTER"));
        //Season1{seasonName='����', seasonDesc='��������'}
        //[LEnumTest.Season1;@15aeb7ab
        /**
         * values()����������ö�����͵Ķ������顣�÷������Ժܷ���ر������е�ö��ֵ��
         */
        System.out.println(Season1.values());
        for (Season1 value : Season1.values()) {
            System.out.println(value);
            //Season1{seasonName='����', seasonDesc='��ů����'}
            //Season1{seasonName='����', seasonDesc='��������'}
            //Season1{seasonName='����', seasonDesc='�����ˬ'}
            //Season1{seasonName='����', seasonDesc='����ѩ��'}
        }


    }
}

//ʹ��enum�ؼ��ֶ���ö����
enum Season1 {
    //1. ����ʾ�г�ö����Ķ�����","�ָ���";"��β
    SPRING("����", "��ů����"),
    SUMMER("����", "��������"),
    ANTUMN("����", "�����ˬ"),
    WINTER("����", "����ѩ��");
    //2. ����Season��������ԣ�private final����
    private final String seasonName;
    private final String seasonDesc;
    //3. ˽�л���Ĺ���������������ֵ
    Season1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //4. �������󣺻�ȡö���������
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    //5. ���������ṩtoString()
    @Override
    public String toString() {
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}