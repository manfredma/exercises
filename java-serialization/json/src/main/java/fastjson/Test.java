package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

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
}
