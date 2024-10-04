package balanceTest;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class HashRing_InLong<T> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(HashRing_InLong.class);
    private static volatile HashRing_InLong instance;
    /**
     * 一般是1000
     */
    private int virtualNode = 20;

    private TreeMap<String, T> virtualNode2RealNode;
    //T
    private List<T> nodeList;

    private HashRing_InLong() {
        this.virtualNode2RealNode = new TreeMap<>();
        this.nodeList = new ArrayList<>();
    }

    public static HashRing_InLong getInstance() {
        if (instance == null) {
            synchronized (HashRing_InLong.class) {
                if (instance == null) {
                    instance = new HashRing_InLong();
                }
            }
        }
        return instance;
    }

    public TreeMap<String, T> getVirtualNode2RealNode() {
        return virtualNode2RealNode;
    }

    public String node2VirtualNode(T node, int index) {
        return "virtual&&" + index + "&&" + node.toString();
    }

    public void setVirtualNode(int virtualNode) {
        this.virtualNode = virtualNode;
    }

    public T getNode(String key) {
        String hash = ConsistencyHashUtil.hashMurMurHash(key);
        SortedMap<String, T> tailMap = this.virtualNode2RealNode.tailMap(hash);
        T node;
        if (tailMap.isEmpty()) {
            node = this.virtualNode2RealNode.get(this.virtualNode2RealNode.firstKey());
        } else {
            node = this.virtualNode2RealNode.get(tailMap.firstKey());
        }
        System.out.printf("%s located to %s\n", key, node);
        return node;
    }

    private synchronized void extendNode(List<T> nodes) {
        this.nodeList.addAll(nodes);
        for (T host : this.nodeList) {
            for (int i = 0; i < this.virtualNode; i++) {
                String key = node2VirtualNode(host, i);
                String hash = ConsistencyHashUtil.hashMurMurHash(key);
                virtualNode2RealNode.put(hash, host);
            }
        }
        System.out.printf("append node list %s\n", nodes);
    }

    private synchronized void removeNode(List<T> hosts) {
        for (T host : hosts) {
            this.nodeList.remove(host);
            for (int i = 0; i < this.virtualNode; i++) {
                String hash = ConsistencyHashUtil.hashMurMurHash(node2VirtualNode(host, i));
                virtualNode2RealNode.remove(hash);
            }
        }
        System.out.printf("remove node list %s\n", hosts);
    }

    public synchronized void updateNode(List<T> nodes) {
        List<T> newHosts = new ArrayList<>(nodes);
        List<T> oldHosts = new ArrayList<>(this.nodeList);
        List<T> append = newHosts.stream().filter(host -> !oldHosts.contains(host)).collect(Collectors.toList());
        List<T> remove = oldHosts.stream().filter(host -> !newHosts.contains(host)).collect(Collectors.toList());
        extendNode(append);
        removeNode(remove);
    }


    static class ConsistencyHashUtil{

        public static String hashMurMurHash(String key, int seed) {
            HashFunction hashFunction = Hashing.murmur3_128(seed);
            return hashFunction.hashString(key, StandardCharsets.UTF_8).toString();
        }

        public static String hashMurMurHash(String key) {
            Random random = new Random(System.currentTimeMillis());
            return hashMurMurHash(key, random.nextInt());
        }

    }

    public static void main(String[] args) {
        List<Integer> nodeLists = IntStream.range(0, 12).boxed().toList();
        HashRing_InLong instance1 = HashRing_InLong.getInstance();
        System.out.println("instance1.nodeList = " + instance1.nodeList);
        System.out.println("instance1.virtualNode = " + instance1.virtualNode);
        System.out.println("instance1.virtualNode2RealNode = " + instance1.virtualNode2RealNode);
        instance1.updateNode(nodeLists);
        System.out.println("------------------------");
        System.out.println("instance1.nodeList = " + instance1.nodeList);
        System.out.println("instance1.virtualNode = " + instance1.virtualNode);
        System.out.println("instance1.virtualNode2RealNode = " + instance1.virtualNode2RealNode);
    }

}