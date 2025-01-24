package manfred.end;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class MapDBOffHeapExample {

    public static void main(String[] args) {
        // 创建一个堆外数据库，并设置缓存策略
        DB db = DBMaker
                .memoryDirectDB()
                .allocateStartSize(100 * 1024 * 1024)  // 初始分配10MB
                .allocateIncrement(5 * 1024 * 1024)   // 每次增长5MB
                .make();

        // 创建一个堆外Map，并设置缓存策略
        HTreeMap<String, String> map = db
                .hashMap("offHeapMap")
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.STRING)
                .expireMaxSize(1002)               // 最多存储1000个元素
                .expireStoreSize(100 * 1024 * 1024)    // 最大存储空间100MB
                .expireAfterCreate(24 * 60 * 60 * 1000) // 24小时后过期
                .createOrOpen();


        // 添加一些数据
        for (int i = 0; i < 1500; i++) {
            map.put("key" + i, "value" + i);
        }

        int size  = 0;
        for (int i = 0; i < 1500; i++) {
            size += map.containsKey("key" + i) ? 1 : 0;
        }
        System.out.println(size);

        // 读取数据
        System.out.println("Value for key1: " + map.get("key1"));
        System.out.println("Value for key500: " + map.get("key500"));
        System.out.println("Value for key1000: " + map.get("key1000"));
        System.out.println("Value for key1499: " + map.get("key1499"));

        // 显示Map的大小
        System.out.println("Map size: " + map.size());

        // 关闭数据库
        db.close();
    }
}