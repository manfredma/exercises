package sync;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


public class HelloClientDemo {

    public static final String SERVER_IP = "localhost";

    public static final int SERVER_PORT = 8090;

    public static final int TIMEOUT = 30000;

    public void startClient(String userName) {
        try (TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT)) {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            String result = client.sayHello(userName);
            System.out.println("Thrift client result =: " + result);
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloClientDemo client = new HelloClientDemo();
        client.startClient("Vilarsail");
    }
}
