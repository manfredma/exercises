package manfred.end.java.io.bio.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Manfred since 2019/8/14
 */
public class Server implements Runnable {
    static final int PORT = 8080;
    private static final int MAX_INPUT = 1024;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (!Thread.interrupted()) {
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Handler implements Runnable {
        final Socket socket;

        Handler(Socket s) {
            this.socket = s;
        }

        @Override
        public void run() {
            try {
                byte[] input = new byte[MAX_INPUT];
                int l = socket.getInputStream().read(input);
                byte[] output = process(input, l);
                socket.getOutputStream().write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private byte[] process(byte[] input, int length) {
            return Arrays.copyOf(input, length);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
