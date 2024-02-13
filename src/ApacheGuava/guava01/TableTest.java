package ApacheGuava.guava01;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * {@link Table} ��� <p>
 * 1����һ�����������Ϊ�м����м����뵥��ֵ������� Map ���ϣ��������ϡ��ģ�ֻ��һС�����м�/�м��Ծ�����Ӧ��ֵ <p>
 * 2��Table �漰�� 3 �����rowKey,columnKey,value��R ��ʶ���ݿ⺽��������C ��������V �� �е�ֵ�� <p>
 * 3��Table �ӿ��кܶ��ʵ�֣����õ��� ��{@link HashBasedTable}��{@link ArrayTable}��{@link TreeBasedTable} �� <p>
 * 4��HashBasedTable �ڲ��ṩ Map< R, Map<C, V>> �ṹ���ڴ洢���ݿ� table �ṹ�����ݣ���ʵ�ֲ�ͬ�����������̷߳��ʴ˱��������ⲿ����ͬ�� <p>
 *
 */
public class TableTest {
    /**
     * HashBasedTable �ڲ��ṩ Map<R, Map<C, V>> �ṹ���ڴ洢���ݿ� table �ṹ������ <p>
     * ��ʵ�ֲ�ͬ�����������̷߳��ʴ˱��������ⲿ����ͬ�� <p>
     */
    @Test
    public void hashBasedTable() {
        HashBasedTable<Object, Object, Object> hashBasedTable = HashBasedTable.create();
        hashBasedTable.put("1001", "name", "չ����");
        hashBasedTable.put("1001", "age", "33");
        hashBasedTable.put("1001", "price", 88898.78);

        hashBasedTable.put("1002", "name", "��");
        hashBasedTable.put("1002", "age", "43");
        hashBasedTable.put("1002", "price", 56898.78);

        //��ȡÿһ�е�������rowKeySet=[1001, 1002]
        Set<Object> rowKeySet = hashBasedTable.rowKeySet();
        System.out.println("rowKeySet=" + rowKeySet);

        //��ȡ�����ƣ�columnKeySet=[name, age, price]
        Set<Object> columnKeySet = hashBasedTable.columnKeySet();
        System.out.println("columnKeySet=" + columnKeySet);

        Collection<Object> values = hashBasedTable.values();
        System.out.println("values = " + values);
        //values = [չ����, 33, 88898.78, ��, 43, 56898.78]

        //hashBasedTable={1001={name=չ����, age=33, price=88898.78}, 1002={name=��, age=43, price=56898.78}}
        System.out.println("hashBasedTable=" + hashBasedTable);

        //����������ȡ�������ݣ�row={name=չ����, age=33, price=88898.78}
        Map<Object, Object> row = hashBasedTable.row("1001");
        System.out.println("row=" + row);

        //����������ȡ���м�¼��ֵ��name={1001=չ����, 1002=��}
        Map<Object, Object> name = hashBasedTable.column("name");
        System.out.println("name=" + name);

        //���������Լ�������ȡ�ֶ�ֵ��name1=չ����
        Object name1 = hashBasedTable.get("1001", "name");
        System.out.println("name1=" + name1);

        //����ÿ����Ԫ��
        Set<Table.Cell<Object, Object, Object>> cellSet = hashBasedTable.cellSet();
        for (Table.Cell<Object, Object, Object> cell : cellSet) {
            System.out.println("rowKey=" + cell.getRowKey() + " - " + cell.getColumnKey() + " - " + cell.getValue());
        }
    }

}
