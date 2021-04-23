package manfred.end.cron;

import static org.slf4j.LoggerFactory.getLogger;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolTest {
    private static final Logger LOGGER = getLogger(ScheduleThreadPoolTest.class);
    @Test
    public void testBasic() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule((Runnable) () -> LOGGER.info("定时任务执行", new Throwable()), 100, TimeUnit.MICROSECONDS);
        TimeUnit.SECONDS.sleep(1);
    }
}
