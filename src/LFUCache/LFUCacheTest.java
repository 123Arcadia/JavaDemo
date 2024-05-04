package LFUCache;

import java.util.HashMap;
import java.util.Map;

public class LFUCacheTest {


}


class LFUCache {
    private final int capacity;

    private final Map<Integer, Node> keyToNode = new HashMap<>();
    //次数 - Node,
    private final Map<Integer, Node> freqToDummy = new HashMap<>();
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) return null;
        Node node = keyToNode.get(key);
        remove(node); // 抽出该书
        // 不是第一次读
        Node dum = freqToDummy.get(node.freq);
        if (dum.prev == dum) {
            freqToDummy.remove(node.freq);
            if (node.freq == minFreq) { // 是最左边的书
                minFreq++;
            }
        }
        pushFront(++node.freq, node); // node插入
        return node;
    }

    private void pushFront(int freq, Node x) {
        // 如果没有就从虚拟节点创建
        Node dum = freqToDummy.computeIfAbsent(freq, s -> newList());
        x.prev = dum;
        x.next = dum.next;
        x.prev.next = x;
        x.next.prev = x;
    }

    private Node newList() {
        Node dum = new Node(0, 0);
        dum.prev = dum;
        dum.next = dum;
        return dum;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return ;
        }
        if (keyToNode.size() == capacity) {
            // 最少使用
            Node dum = freqToDummy.get(minFreq);
            Node backNode = dum.prev; // 左边最底层的书
            keyToNode.remove(backNode.key);
            remove(backNode);
            if (dum.prev == dum) { // 这边书是空的
                freqToDummy.remove(minFreq);
            }
        }
        node = new Node(key, value);
        keyToNode.put(key, node);
        pushFront(1, node);
        minFreq = 1;

    }

    class Node {

        int key;
        int value;
        int freq = 1; // 新书只有一次
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}