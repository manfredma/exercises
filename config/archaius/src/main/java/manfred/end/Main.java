package manfred.end;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DynamicIntProperty myAge = DynamicPropertyFactory.getInstance().getIntProperty("my.age", 18);
        System.out.println(myAge);
        System.out.println(myAge.get());

        TimeUnit.SECONDS.sleep(80);
        System.out.println("动态修改后的值为：");
        System.out.println(myAge);
        System.out.println(myAge.get());
    }
}