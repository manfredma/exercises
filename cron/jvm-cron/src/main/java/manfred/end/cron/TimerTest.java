package manfred.end.cron;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTest {
    private static final org.slf4j.Logger LOGGER =
            org.slf4j.LoggerFactory.getLogger(TimerTest.class);

    @Test
    public void testBasic() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOGGER.info("定时任务执行", new Throwable());
            }
        }, 100);
        TimeUnit.SECONDS.sleep(1);
    }
}
