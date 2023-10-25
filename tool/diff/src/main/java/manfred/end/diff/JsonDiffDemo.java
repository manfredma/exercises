package manfred.end.diff;

import com.alibaba.fastjson2.JSON;
import me.codeleep.jsondiff.DefaultJsonDifference;
import me.codeleep.jsondiff.model.JsonCompareResult;
import me.codeleep.jsondiff.model.JsonComparedOption;

public class JsonDiffDemo {
    public static void main(String[] args) {
        // https://www.leyeah.com/article/simple-use-json-diff-java-and-consistency-objects-detail-705471
        String array1 = "[1, 2, 3, 4, 5]";
        String array2 = "[1, 3, 9, 4, 5]";
        JsonComparedOption jsonComparedOption = new JsonComparedOption().setIgnoreOrder(true);
        JsonCompareResult jsonCompareResult = new DefaultJsonDifference()
                .option(jsonComparedOption)
                .detectDiff(JSON.parseArray(array1), JSON.parseArray(array2));
        System.out.println(JSON.toJSONString(jsonCompareResult));
    }
}
