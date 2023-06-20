package smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

        String details = "--------------零钱通明细-------------------";

        double money = 0;
        double balance = 0;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //用于格式化时间和日期
        //yyyy:表示年, MM:表示月, dd:表示日, HH:时, mm:秒

        //消费
        String note = "";

        do {
            System.out.println("===========零钱通菜单===========");
            System.out.println("\t\t1.零钱通明细");
            System.out.println("\t\t2.收益入账");
            System.out.println("\t\t3.消费");
            System.out.println("\t\t4.退     出");
            System.out.println("请选择（1 - 4）");
            key = scanner.next();

            switch (key) {
                case "1":
                    System.out.println("1.零钱通明细");
                    System.out.println(details);

                    break;
                case "2":
                    System.out.println("2.收益入账");

                    money = scanner.nextDouble();
                    balance += money;
                    System.out.println("收益入账金额:" + money);
                    date = new Date();
                    //System.out.println(sdf.format(date));
                    details += "\n收益入账\t+" + money + "\t" + sdf.format((date)) + "\t" + "余额:" + balance;
                    break;
                case "3":
                    System.out.print("3.消费金额:");
                    money =  scanner.nextDouble();

                    System.out.print("消费说明:");
                    note = scanner.next();
                    balance -= money;
                    date = new Date();
                    details += "\n" + note + "\t-" + money + "\t" + sdf.format((date)) + "\t" + "余额:" + balance;
                    System.out.println("此次消费:" + details);

                    break;
                case "4":
                    System.out.println("4.退     出");
                    break;
                default:
                    System.out.println("输入有误，重新输入");
            }
        }while (loop) ;

            System.out.println("exit()...");

    }
}
