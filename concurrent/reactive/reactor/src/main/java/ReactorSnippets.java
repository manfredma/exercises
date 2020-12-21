import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.util.Arrays;
import java.util.List;

public class ReactorSnippets {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    @Test
    public void simpleCreation() {
        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);


        fewWords.subscribe(a -> {
            System.out.println(Thread.currentThread() + ": " + a);
        });

        System.out.println("++++++++++++++++++++++++++++++++++++");
        manyWords.subscribe(a -> {
            System.out.println(Thread.currentThread() + ": " + a);
        });
    }
}