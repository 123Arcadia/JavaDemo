package List.StuTestForEach;

import java.util.*;

public class DAO<T> {

    private Map<String, T> map = new HashMap<String, T>();

    //保存T类型断点对象大Map的成员变量中
    public void save(String id, T entity) {
        map.put(id, entity);
    }

    //从map中获取id对应的对象
    public T get (String id) {
        return map.get(id);
    }

    //替换map中key为id的内容，改为entity对象
    public void update(String id, T entity) {
        if(map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    //删除map中存放的所有T对象
    public List<T> list() {
        ArrayList<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for(T i:values) {
            list.add(i);
        }
        return list;
    }

    //删除指定id的对象
    public void delete(String id) {
        map.remove(id);
    }
}
