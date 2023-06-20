package List.StuTestForEach;

import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<User>();

        dao.save("1001", new User(1001, 34, "zhoujielun"));
        dao.save("1002", new User(1002, 20, "zcw01"));
        dao.save("1003", new User(1003, 34, "zcw02"));


        List<User> list = dao.list();
        list.forEach(System.out::println);
    }

}
