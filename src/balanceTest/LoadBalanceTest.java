package balanceTest;

import java.util.List;

public interface LoadBalanceTest {

    public ServerNode select(List<ServerNode> serverNodes);
}
