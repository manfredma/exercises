package manfred.end.java.io.bio.net;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Manfred since 2019/8/14
 */
public class Client implements Runnable {

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", Server.PORT);
            socket.getOutputStream().write("hello, world".getBytes());
            byte[] s = new byte[1024];
            int l = socket.getInputStream().read(s);
            System.out.println(new String(s, 0, l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.run();
    }
}
