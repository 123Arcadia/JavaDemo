package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class iterator01 {
    public static void main(String[] args) {
        Collection col = new ArrayList();

        col.add(new Book("西游记", "罗贯中", 20));
        col.add(new Book("y", "Auth_y", 19));
        col.add(new Book("x", "Auth_x", 17));

        //System.out.println("list = " + col);
        //list = [Collection.Book@682a0b20, Collection.Book@3d075dc0, Collection.Book@214c265e]


        Iterator iterator = col.iterator();

//        while (iterator.hasNext()) {
//            Object obj = iterator.next();
//            System.out.println( "obj =" + obj);
//        }
        //快速生成while -> iterator : itit
        while (iterator.hasNext()) {
            Object next =  iterator.next();

        }
        for (Object book : col) {
            System.out.println(book);
        }

    }
}
class Book {
    private String name;
    private String author;
    private int price;


    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
