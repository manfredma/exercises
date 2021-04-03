package manfred.end.guava.base;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author manfred on 2021/4/3.
 */
public class OptionalTest {

    @Test
    public void simpleTest() {
        Optional<Object> objectOptional = Optional.of(new Object());
        System.out.println(objectOptional.get());
    }

    @Test
    public void setTest() {
        Optional<Object> objectOptional = Optional.of(new Object());
        System.out.println(objectOptional.asSet());
    }

    @Test
    public void iterableTest() {
        Optional<Object> objectOptional = Optional.of(new Object());
        Optional.presentInstances(Lists.newArrayList(objectOptional, objectOptional)).forEach(
                System.out::println
        );
    }
}
