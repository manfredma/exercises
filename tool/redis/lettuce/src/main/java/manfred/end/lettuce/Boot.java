package manfred.end.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;

public class Boot {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RedisClient redisClient = RedisClient
                .create("redis://password@localhost:6379/");
        StatefulRedisConnection<String, String> connection
                = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("key", "Hello, Redis!");
        String value = syncCommands.get("key");
        System.out.println(value);

        RedisAsyncCommands<String, String> asyncCommands = connection.async();

        RedisFuture<String> valueFuture = asyncCommands.get("key");
        valueFuture
                .thenAccept(System.out::println)
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return null;
                });
    }
}
