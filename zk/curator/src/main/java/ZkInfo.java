import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @author manfred
 * @since 2020-04-08 6:21 下午
 */
public class ZkInfo {
    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181",
                retryPolicy);
        curatorFramework.start();
        try {
            String data = new String(curatorFramework.getData().forPath("/dubbo"));
            System.out.println(data);
            List<String> children = curatorFramework.getChildren().forPath("/dubbo");
            System.out.println(children);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
