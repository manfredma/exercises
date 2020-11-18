import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

public class HelloServerDemo {

    public static final int SERVER_PORT = 8090;

    public static void main(String[] args) throws IOException, TTransportException {

        ServerSocket socket = new ServerSocket(SERVER_PORT);
        TServerSocket serverTransport = new TServerSocket(socket);
        HelloWorldService.Processor<HelloWorldService.Iface> processor = new HelloWorldService.Processor<>(new HelloWorldImpl());
        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());
        TServer server = new TSimpleServer(tArgs);

        System.out.println("Running server...");
        server.serve();
    }
}