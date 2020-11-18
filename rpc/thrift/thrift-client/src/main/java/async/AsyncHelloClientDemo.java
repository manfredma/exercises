package async;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author manfred
 * @since 2019-11-10 下午4:53
 */
public class AsyncHelloClientDemo {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;

    @Test
    public void startClient() throws Exception {

        // 定义链接
        TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
        // 定义协议：主要是序列化相关
        TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
        // 这个的功能主要是什么？
        TAsyncClientManager clientManager = new TAsyncClientManager();

        HelloWorldService.AsyncClient client = new HelloWorldService.AsyncClient(protocolFactory, clientManager, transport);
        client.sayHello("manfred", new AsyncMethodCallback<String>() {

            @Override
            public void onComplete(String response) {
                System.out.println("AsyncMethodCallback#onComplete invoked, response = " + response);
            }

            @Override
            public void onError(Exception exception) {
                System.out.println("AsyncMethodCallback#onError invoked, error = " + exception);
            }
        });

        TimeUnit.SECONDS.sleep(10);
    }

}
