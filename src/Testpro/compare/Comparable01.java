package Testpro.compare;

import java.util.Arrays;

public class Comparable01 {
    public static void main(String[] args) {
        Goods[] arr = new Goods[4];
        arr[0] = new Goods("lenovo", 34);
        arr[1] = new Goods("dell", 43);
        arr[2] = new Goods("xiaomi", 12);
        arr[3] = new Goods("huawei", 65);
        
        Arrays.sort(arr);
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    }
}

class Goods implements Comparable {
    private double price;
    private String name;

    public Goods(String name, double price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Goods{" + "name" + name + '\'' + ", price = " + price + '}';
    }

    /**
     * 升序
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods goods = (Goods)o;
//            if (this.price > goods.price) return -1;
//            else if (this.price < goods.price) {
//                return 1;
//            } else{
//                return 0;
//            }

//            return (int) (this.price - ((Goods) o).price);

            return Double.compare(this.price,goods.price);
        }
        throw new RuntimeException("传入的数据不一致");
    }
}