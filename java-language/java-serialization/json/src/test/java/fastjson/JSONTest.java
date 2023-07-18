package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

/**
 * @author manfred
 * @since 2019-11-06 下午2:34
 */
public class JSONTest {

    @Test
    public void testBasic() {
        System.out.println(JSON.toJSON("xxxx"));
        System.out.println(JSON.toJSON("xxxx"));

        System.out.println(JSONArray.isValid("{\"xxx\":\"yx\",\"0\":\"y\"}"));
        System.out.println(JSONArray.isValidArray("{\"xxx\":\"yx\",\"0\":\"y\"}"));
        System.out.println(JSONArray.isValidObject("{\"xxx\":\"yx\",\"0\":\"y\"}"));
        System.out.println();
        System.out.println(JSONArray.isValid("[{\"name\":\"\\u62bc\\u91d1\",\"fee\":20000}]"));
        System.out.println(JSONArray.isValidArray("[{\"name\":\"\\u62bc\\u91d1\",\"fee\":20000}]"));
        System.out.println(JSONArray.isValidObject("[{\"name\":\"\\u62bc\\u91d1\",\"fee\":20000}]"));

        System.out.println();
    }

    @Test
    public void testDeSerialization() {
        Map<String, Object> xxx = new HashMap<>();
        xxx.put("1", "1");
        Map<String, Object> xxx2 = new HashMap<>(xxx);
        xxx.put("map", xxx2);

        JSONObject v = JSON.parseObject(JSON.toJSONString(xxx));
        for (Map.Entry<String, Object> stringObjectEntry : v.entrySet()) {
            System.out.println(stringObjectEntry.getKey().getClass() + ":" + stringObjectEntry.getValue().getClass());
        }
        System.out.println(xxx);
    }

    @Test
    public void testSerialization() {
        Map<String, Object> xxx = new HashMap<>();
        xxx.put("2", null);
        xxx.put("1", "1");
        Map<String, Object> xxx2 = new HashMap<>(xxx);
        xxx.put("map", xxx2);

        System.out.println(JSON.toJSONString(xxx));
        JSONObject v = JSON.parseObject(JSON.toJSONString(xxx));
        for (Map.Entry<String, Object> stringObjectEntry : v.entrySet()) {
            System.out.println(stringObjectEntry.getKey().getClass() + ":" + stringObjectEntry.getValue().getClass());
        }
        System.out.println(xxx);
    }

    @Test
    public void testTreeMap() {
        Map<String, Object> xxx = new TreeMap<>();
        xxx.put("3", "3");
        xxx.put("2", null);
        xxx.put("1", "1");
        xxx.put("4", "4");
        Map<String, Object> xxx2 = new TreeMap<>(xxx);
        xxx.put("map", xxx2);
        xxx.put("n", "n");
        xxx.put("l", "l");
        System.out.println(JSON.toJSONString(xxx));
    }

    @Test
    public void testTreeMap2() {
        Map<String, Object> xxx = new HashMap<>();
        xxx.put("3", "3");
        xxx.put("2", null);
        xxx.put("1", "1");
        xxx.put("4", "4");
        Map<String, Object> xxx2 = new HashMap<>(xxx);
        xxx.put("map", xxx2);
        xxx.put("n", "n");
        xxx.put("l", "l");
        System.out.println(JSON.toJSONString(xxx));
        System.out.println(JSON.toJSONString(JSONObject.parseObject(JSON.toJSONString(xxx))));
    }

    @Test
    public void testString2Integer () {
        A a = JSON.parseObject("{'a':'34124123'}", A.class);
        System.out.println(a);
    }

    public static class A {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
