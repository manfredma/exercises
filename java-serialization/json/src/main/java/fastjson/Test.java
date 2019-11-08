package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author manfred
 * @since 2019-11-06 下午2:34
 */
public class Test {
    public static void main(String[] args) {
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

    @org.junit.Test
    public void testDeSerialization() {
        Map<String, Object> xxx = new HashMap<>();
        xxx.put("1", "1");
        Map<String, Object> xxx2 = new HashMap<>(xxx);
        xxx.put("map", xxx2);

        JSONObject v = JSON.parseObject(JSON.toJSONString(xxx));
        for (Map.Entry<String, Object> stringObjectEntry : v.entrySet()) {
            System.out.println(stringObjectEntry.getKey().getClass() + ":" + stringObjectEntry.getValue().getClass());
        }
        System.out.println(v);
    }
}
