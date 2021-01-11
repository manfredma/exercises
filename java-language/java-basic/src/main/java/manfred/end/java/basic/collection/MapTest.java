package manfred.end.java.basic.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Manfred since 2018/3/15.
 */
public class MapTest {

    /**
     * Hash Map 可以插入 null 到 value
     */
    @Test
    public void testInsertNullIntoHashMap() {
        Map<String, String> map = new HashMap<String, String>(12);
        map.put("aaa", "bbb");
        map.put("ccc", null);
        Assert.assertTrue(map.containsKey("ccc"));
    }
}
