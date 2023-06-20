package staticTest;

public class staticTesting {
    public static void main(String[] args) {

        stu stu1 = new stu("zcw1");
        stu1.payFee(50);

        stu stu2 = new stu("zcw2");
        stu2.payFee(100);
        stu.showFee();
    }
}
class stu {
    private String name;
    public static double fee = 0;
    public stu(String name) {
        this.name = name;
    }

    public static void payFee(int fee) {
        stu.fee += fee;
    }

    public static void showFee() {
        System.out.println(stu.fee);
    }

}




