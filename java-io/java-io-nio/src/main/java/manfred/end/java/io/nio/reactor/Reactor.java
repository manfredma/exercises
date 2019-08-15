package manfred.end.java.io.nio.reactor;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author Manfred since 2019/8/14
 */
public class Reactor implements Runnable {
    final Selector selector;

    final ServerSocketChannel serverSocket;

    private static final int MAXIN = 1024;

    private static final int MAXOUT = 1024;


    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                for (SelectionKey selectionKey : selected) {
                    dispatch(selectionKey);
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(8080);
        reactor.run();
    }

    void dispatch(SelectionKey k) {
        Runnable r = (Runnable) k.attachment();
        if (null != r) {
            r.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (null != c) {
//                    new Handler(selector, c);
                    new HandlerState(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable {
        final SocketChannel socket;

        final SelectionKey sk;

        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);

        static final int READING = 0, SENDIND = 1;
        int state = READING;

        Handler(Selector sel, SocketChannel c) throws IOException {
            socket = c;
            c.configureBlocking(false);

            // Optionally try first read now
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else if (state == SENDIND) {
                    send();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDIND;
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        void send() throws IOException {
            output.put("manfred".getBytes());
            output.flip();
            socket.write(output);
            if (outputIsCompete()) {
                sk.cancel();
            }
        }

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsCompete() {
            return true;
        }

        void process() {
            input.flip();
            byte[] bytes = new byte[input.limit()];
            input.get(bytes);
            System.out.println(new String(bytes));
        }
    }

    class HandlerState implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);

        HandlerState(Selector sel, SocketChannel c) throws IOException {
            socket = c;
            c.configureBlocking(false);
            sk = socket.register(sel, SelectionKey.OP_READ);
            sk.attach(this);
        }

        @Override
        public void run() {
            try {
                socket.read(input);
                if (inputIsComplete()) {
                    process();
                    sk.attach(new Sender());
                    sk.interestOps(SelectionKey.OP_WRITE);
                    sk.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsCompete() {
            return true;
        }

        void process() {
            input.flip();
            byte[] bytes = new byte[input.limit()];
            input.get(bytes);
            System.out.println(new String(bytes));
        }

        class Sender implements Runnable {
            @Override
            public void run() {
                output.put("manfred".getBytes());
                output.flip();
                try {
                    socket.write(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (outputIsCompete()) {
                    sk.cancel();
                }
            }
        }
    }
}
