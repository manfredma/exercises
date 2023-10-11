package manfred.end.yml;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class SnakeYmlDemo {
    public static void main(String[] args) {
        // System.getProperties().forEach((k, v) -> System.out.println(k + " = " + v));
        // 读取 YAML 文件
        try (InputStream inputStream = Files.newInputStream(Paths.get("tool/yml/example.yaml"))) {
            Yaml yaml = new Yaml();
            Iterable<Object> data = yaml.loadAll(inputStream);
            // 处理 YAML 数据
            data.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 写入 YAML 文件
        try (Writer writer = new FileWriter("tmp/output.yaml")) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = createData();
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static Map<String, Object> createData() {
        Map<String, Object> data = new HashMap<>();
        data.put("hello", "world");
        data.put("arr", new String[]{"a", "b", "c"});
        return data;
    }
}
