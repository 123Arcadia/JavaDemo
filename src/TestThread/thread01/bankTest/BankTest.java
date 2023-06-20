package TestThread.thread01.bankTest;

public class BankTest {
    public static void main(String[] args) {

    }
}

class Bank {
    private Bank() {}

    private static Bank instance  = null;


    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

}
