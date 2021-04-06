package omit.exception.stack;

import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.Logger;

public class Main {

    private static final Logger LOGGER = getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            try {
                count++; //统计调用次数
                error();
            } catch (Exception e) {
                if (e.getStackTrace().length == 0) {
                    LOGGER.info("no trace count:{}", count, e);
                    Thread.sleep(1000); //方便观察日志
                } else {
                    LOGGER.info("full trace count:{}", count, e);
                }
            }
        }
    }

    private static void error() {
        String nullMsg = null;
        nullMsg.toString();
    }

}
