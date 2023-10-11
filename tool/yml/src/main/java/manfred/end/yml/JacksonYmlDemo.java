package manfred.end.yml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonYmlDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        // 读取 YAML 文件
        try {
            List<Object> data = mapper.readValues(new YAMLFactory().createParser(new File("tool/yml/example.yaml")), Object.class).readAll();
            // 处理 YAML 数据
            data.forEach(d -> System.out.println(d.getClass() + " : " + d));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入 YAML 文件
        try {
            Map<String, Object> data = createData();
            mapper.writeValue(new File("tmp/output.yaml"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<String, Object> createData() {
        Map<String, Object> data = new HashMap<>();
        data.put("hello", "jackson-yml");
        data.put("arr", new String[]{"a", "b", "c"});
        return data;
    }
}
