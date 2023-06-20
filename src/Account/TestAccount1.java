package Account;

public class TestAccount1 extends Account1{
//    public void TestAccount() {
//        super.cal();
//        this.cal();
//    }
    public void cal() {
        System.out.println("B -> cal()...");
    }

    public static void main(String[] args) {
        Account1 account1 = new Account1("jack", 100, "123456");
        account1.cal();
        System.out.println(account1.getBalance());
        System.out.println(account1.getName());
        System.out.println(account1.getPwd());
        TestAccount1 testAccount1 = new TestAccount1();
        testAccount1.cal();

        System.out.println(account1);  //默认调用toString
        System.out.println(account1.toString());
    }

}
