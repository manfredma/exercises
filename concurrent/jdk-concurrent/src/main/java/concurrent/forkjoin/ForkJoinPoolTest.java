package concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {

	public static void main(String[] args) throws Exception{
		testNoResultTask();
	}

	private static void testNoResultTask() throws InterruptedException{
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new PrintTask(1,50));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		pool.shutdown();
	}
}
