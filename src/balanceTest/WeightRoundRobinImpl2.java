package balanceTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WeightRoundRobinImpl2 implements  LoadBalanceTest{


    private static AtomicInteger useCnt = new AtomicInteger(0);
    public static List<ServerNode> servers_global = new ArrayList<>();
    @Override
    public ServerNode select(List<ServerNode> serverNodes) {
        int count = servers_global.size();

        if (count  <= 0) return null;
        int total_weight = 0;
        for (ServerNode node : servers_global) {
            total_weight += node.getWeight();
        }
        int curIdx = useCnt.incrementAndGet() % total_weight;
        System.out.println("[init]curIdx = " + curIdx + ", total_w=" +total_weight);
        for (ServerNode node : servers_global) {
            String hostname = node.getHostname();
            int w = node.getWeight();
            System.out.println("curIdx=" +curIdx +", hostName=" + hostname +", w=" +w);
            curIdx -= w;
            System.out.println("curIdx = " + curIdx);
            if (curIdx <0) {
                return node;
            }
        }
        return null;

    }



    public static void main(String[] args) {
        WeightRoundRobinImpl2 lb = new WeightRoundRobinImpl2();
        List<ServerNode> servers = new ArrayList<>();
        // 初始化3个节点：a、b、c，权重分别是：1、2、4
        servers.add(new ServerNode("a", 1, 0));
        servers.add(new ServerNode("b", 2, 0));
        servers.add(new ServerNode("c", 3, 0));
        servers_global = servers;
        int N = 12;
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
//            if (i % 7 == 0) {
//                System.out.println(String.format("\n %s th Request======:", (i / 7) + 1));
//            }
            ServerNode selected = lb.select(servers_global);
            System.out.println(String.format(" %s th, chose: %s", i + 1, selected.getHostname()));
            countMap.put(selected.getHostname(), countMap.getOrDefault(selected.getHostname(), 0) +1);
        }
        System.out.println(countMap);
        //{a=2, b=4, c=9}
        servers_global.forEach(s-> System.out.println(s.getHostname()+ ", curW: " + s.getCurrentWeight() + ", w: " + s.getWeight()));
    }
}
