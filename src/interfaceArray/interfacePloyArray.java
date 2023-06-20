package interfaceArray;

/**
 * 向下转型
 */
public class interfacePloyArray {
    public static void main(String[] args) {
            Usb[] usbs = new Usb[2];
            usbs[0] = new Phone_();
            usbs[1] = new Camera_();


            for (int i = 0 ;i < usbs.length; ++i) {
                usbs[i].work(); //动态绑定
                if (usbs[i] instanceof Phone_) { //usbs是Usb类型，要向下转型
                    ((Phone_)usbs[i]).call();
                }
            }
        //Phone is calling...
        //Phone is calling...(not Override)
        //Camera is calling...
    }
}
interface  Usb {
    void work();
}
class Phone_ implements  Usb {
    public void call() {
        System.out.println("Phone is calling...(not Override)");
    }

    @Override
    public  void work() {
        System.out.println("Phone is calling...");
    }
}
class Camera_ implements  Usb {
    @Override
    public  void work() {
        System.out.println("Camera is calling...");
    }
}
