package balanceTest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author chenwenxin
 * @since 2020-03-26 16:50
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ServerNode {

    private String hostname;

    /** 设置的weight */
    private int weight;

    /** 当前weight */
    private int currentWeight;

    public void selected(int total) {
        currentWeight -= total;
    }

    public void increaseCurrentWeight() {
        currentWeight += weight;
    }
}


/**
 * 负载均衡 - 加权轮询算法
 */
public class WeightRoundRobinLoadBalanceTest implements LoadBalanceTest {

    /**
     * 两步:
     * 1. 每个self.weigth += self.weight
     * 2. 本轮选择最大的maxWeight的节点
     * 3. 更新新的weight和totalWeight
     * 2. 更新选择的最大 权重节点的weight: self.weight -= totalWeight
     * @param serverNodes
     * @return
     */
    @Override
    public ServerNode select(List<ServerNode> serverNodes) {
        ServerNode serverNodeSelected = null;
        int maxWeight = Integer.MIN_VALUE;
        int totalWeight = 0;
        for (ServerNode serverNode : serverNodes) {
            // 增加各自的 currentWeight
            serverNode.increaseCurrentWeight();
            // 更新最大权重和最大权重的节点
            if (serverNode.getCurrentWeight() > maxWeight) {
                maxWeight = serverNode.getCurrentWeight();
                serverNodeSelected = serverNode;
            }
            totalWeight += serverNode.getWeight();
        }
        if (serverNodeSelected != null) {
            // 被选中的节点，currentWeight 需要减去所有 weight 之和
            serverNodeSelected.selected(totalWeight);
            // 3 - 6 =  -3
            return serverNodeSelected;
        }
        // should not happen here
        return serverNodes.get(0);
    }

    // 测试
    public static void main(String[] args) {
        WeightRoundRobinLoadBalanceTest lb = new WeightRoundRobinLoadBalanceTest();
        List<ServerNode> servers = new ArrayList<>();
        // 初始化3个节点：a、b、c，权重分别是：1、2、4
        servers.add(new ServerNode("a", 1, 0));
        servers.add(new ServerNode("b", 2, 0));
        servers.add(new ServerNode("c", 3, 0));
        int N = 12;
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
//            if (i % 7 == 0) {
//                System.out.println(String.format("\n %s th Request======:", (i / 7) + 1));
//            }
            ServerNode selected = lb.select(servers);
            System.out.println(String.format(" %s th, chose: %s", i + 1, selected.getHostname()));
            countMap.put(selected.getHostname(), countMap.getOrDefault(selected.getHostname(), 0) +1);
        }
        System.out.println(countMap);
        //{a=2, b=4, c=9}
        servers.forEach(s-> System.out.println(s.getHostname()+ ", curW: " + s.getCurrentWeight() + ", w: " + s.getWeight()));
    }
}
