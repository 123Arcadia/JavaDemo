package Account;

public class Account1 {
    private String name;
    private double balance;
    private String pwd;


    public String getName() {
        return name;
    }

    public Account1() {
    }

    public Account1(String name, double balance, String pwd) {
        this.setName(name);
        this.setBalance(balance);
        this.setPwd(pwd);
    }

    public void cal() {
        System.out.println("A -> cal()...");

    }
    public void setName(String name) {
        if (name.length() >= 2 && name.length() <= 4) {
            this.name = name;
        } else {
            System.out.println("姓名必须在2~4之间，默认： 无名");
            this.name = "无名";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance > 20)
            this.balance = balance;
        else System.out.println("余额不足20，默认: 0");
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        if (pwd.length() == 6)
            this.pwd = pwd;
        else System.out.println("必须6位");
    }
}
