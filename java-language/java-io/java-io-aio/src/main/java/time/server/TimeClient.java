package time.server;

/**
 * @author manfred on 2019/9/10.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }

        new Thread(new AsyncTimeClientHandler("127.0.0.1", port)).start();
    }
}
