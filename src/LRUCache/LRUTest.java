package LRUCache;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class DLinkedNode {
    String key;
    int value;
    DLinkedNode pre;
    DLinkedNode post;
}

public class LRUTest {
    //一般不用hashTable
//    private Hashtable<Integer, DLinkedNode> cache = new Hashtable<Integer, DLinkedNode>();
    private static Map<Integer, DLinkedNode> cache = new ConcurrentHashMap<>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    /**
     * 内存容量
     */
    public LRUTest(int capacity) {
        this.capacity = capacity;
        this.count = 0;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.pre = head;
    }

    public int get(String key) {
        DLinkedNode node = cache.get(Integer.valueOf(key));
        if (node == null) {
            return -1;
        }

        //移动节点向头部head
        this.moveToHead(node);
        return node.value;
    }

    public void set(String key, int value) {
        DLinkedNode node = cache.get(Integer.valueOf(key));
        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(Integer.valueOf(key), newNode);
            this.addNode(newNode);

            count++;

            if (count > capacity) {
                //要开始清除
                DLinkedNode tail = this.popTail();
                cache.remove(Integer.valueOf(tail.key));
                count--;
            }
        } else {
            //更新value
            node.value = value;
            this.moveToHead(node);
        }
    }

    //newNode加在head节点之后
    public void addNode(DLinkedNode node) {
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    //删除节点
    public void removeToHead(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    //把节点移动到head
    public void moveToHead(DLinkedNode node) {
        this.removeToHead(node);
        this.addNode(node);
    }

    //删除当前最不常用的节点
    public DLinkedNode popTail() {
        DLinkedNode res = tail.pre;
        this.removeToHead(res);
        return res;
    }
}
