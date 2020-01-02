import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author manfred
 * @since 2019-12-27 下午7:00
 */
public class Main {
    @Test
    public void mergeMapValuesTest() {
        List<List<String>> xx = new ArrayList<>();
        List<String> xx1 = new ArrayList<>();
        xx1.add("xxx");
        xx.add(xx1);

        List<String> xxS = xx.stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(xxS);
    }
}
