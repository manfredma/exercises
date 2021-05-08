package redis.boot;
import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
        Jedis client = new Jedis();
        client.set("KEY", "VALUE");
    }

}