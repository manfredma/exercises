package manfred.end.diff;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

public class MapDiffDemo {
    public static void main(String[] args) {
        /*
         * difference
         * Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象
         */
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("马六", 752);
        map1.put("张三", 123);
        map1.put("李四", 457);
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("马六", 752);
        map2.put("张三", 345);
        map2.put("田七", 125);
        MapDifference<String, Object> difference = Maps.difference(map1, map2);
        // 是否有差异，返回boolean
        boolean areEqual = difference.areEqual();
        System.out.println("比较两个Map是否相同:" + areEqual);

        // 两个map的交集
        Map<String, Object> entriesInCommon = difference.entriesInCommon();
        System.out.println("两个map都有的部分（交集）===：" + entriesInCommon);

        // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        Map<String, MapDifference.ValueDifference<Object>> entriesDiffering =
                difference.entriesDiffering();
        System.out.println("键相同但是值不同值映射项===：" + entriesDiffering);
        // 键只存在于左边Map的映射项
        Map<String, Object> onlyOnLeft = difference.entriesOnlyOnLeft();
        System.out.println("键只存在于左边Map的映射项:" + onlyOnLeft);
        // 键只存在于右边Map的映射项
        Map<String, Object> entriesOnlyOnRight = difference.entriesOnlyOnRight();
        System.out.println("键只存在于右边Map的映射项:" + entriesOnlyOnRight);

    }
}
