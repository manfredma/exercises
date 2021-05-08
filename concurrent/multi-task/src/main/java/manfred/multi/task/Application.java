package manfred.multi.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author manfred
 */
@SpringBootApplication(scanBasePackages = {"manfred.multi.task"})
public class Application {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        ParallelPool parallelPool = applicationContext.getBean("realNameQueryPool", ParallelPool.class);
        MultiFutureTask<Integer> multiFutureTask = parallelPool.submit(() -> {
            int x = (int) (Math.random() * 10.0);
            System.out.println("1 thread -- " + Thread.currentThread().getId() + " will sleep " + x + " s");
            TimeUnit.SECONDS.sleep(x);
            return x;
        }, () -> {
            int x = (int) (Math.random() * 10.0);
            System.out.println("2 thread -- " + Thread.currentThread().getId() + " will sleep " + x + " s");
            TimeUnit.SECONDS.sleep(x);
            return x;
        }, () -> {
            int x = (int) (Math.random() * 10.0);
            System.out.println("3 thread -- " + Thread.currentThread().getId() + " will sleep " + x + " s");
            TimeUnit.SECONDS.sleep(x);
            return x;
        });
        try {
            System.out.println(multiFutureTask.getInDefaultTimeout());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(multiFutureTask.takeCompleted().get());
        }




    }

}
