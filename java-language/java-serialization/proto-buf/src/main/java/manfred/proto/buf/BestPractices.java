package manfred.proto.buf;

import com.google.protobuf.*;

/**
 * Protocol Buffers Java 最佳实践指南
 *
 * ============================================
 * 核心问题：必须生成代码吗？
 * ============================================
 * 答案：YES，必须生成！
 *
 * 工作流程：
 * 1. 编写 .proto 文件 (src/main/proto/xxx.proto)
 * 2. Maven protobuf-maven-plugin 自动调用 protoc 编译器
 * 3. 生成的 Java 代码输出到 target/generated-sources/
 * 4. 应用代码使用生成的 Message 类
 *
 * 不要手写生成的代码！这会导致：
 * - 维护成本高
 * - 容易出错
 * - 无法同步 proto 定义
 * ============================================
 */
public class BestPractices {

    /**
     * 最佳实践 #1: 使用 Builder 模式创建消息对象
     *
     * 优点：
     * - 不可变对象 (immutable)，线程安全
     * - 自动空值检查
     * - 支持链式调用
     * - 更易于测试
     */
    public static class BuilderPatternExample {
        /*
        // 反例 - 不要这样做（虽然生成的代码可能支持，但不推荐）
        PersonModel.Person person = new PersonModel.Person();
        person.setId(1);  // 如果生成代码支持，也不推荐

        // 正例 - 使用 Builder 模式
        PersonModel.Person person = PersonModel.Person.newBuilder()
                .setId(1)
                .setName("zhangsan")
                .setEmail("zhangsan@example.com")
                .build();
         */
    }

    /**
     * 最佳实践 #2: 正确处理重复字段 (Repeated Fields)
     *
     * 对应 .proto 定义中的 repeated 字段
     * 例如：repeated string address = 4;
     */
    public static class RepeatedFieldsExample {
        /*
        // 添加单个元素
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.addAddress("北京");
        builder.addAddress("上海");
        builder.addAddress("深圳");

        // 添加集合
        builder.addAllAddress(Arrays.asList("成都", "杭州"));

        // 清空重复字段
        builder.clearAddress();

        // 获取重复字段
        List<String> addresses = builder.getAddressList();

        // 获取指定索引的元素
        String firstAddress = builder.getAddress(0);

        // 替换指定索引的元素
        builder.setAddress(0, "新地址");

        // 获取字段元素个数
        int count = builder.getAddressCount();
         */
    }

    /**
     * 最佳实践 #3: 正确的序列化与反序列化
     */
    public static class SerializationExample {
        /*
        // ✅ 推荐的序列化方式
        PersonModel.Person person = PersonModel.Person.newBuilder()
                .setId(1)
                .setName("test")
                .build();

        // 序列化为字节数组
        byte[] bytes = person.toByteArray();

        // 反序列化
        PersonModel.Person person2 = PersonModel.Person.parseFrom(bytes);

        // 增量解析（流式处理）- 适合大文件
        CodedInputStream input = CodedInputStream.newInstance(bytes);
        PersonModel.Person person3 = PersonModel.Person.parseFrom(input);

        // 其他序列化格式
        String jsonFormat = JsonFormat.printer().print(person);  // JSON 格式
         */
    }

    /**
     * 最佳实践 #4: 消息合并与部分更新
     */
    public static class MergeAndUpdateExample {
        /*
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder()
                .setId(1)
                .setName("original");

        PersonModel.Person toMerge = PersonModel.Person.newBuilder()
                .setEmail("new@example.com")
                .build();

        // 合并消息（只会覆盖设置了的字段）
        builder.mergeFrom(toMerge);

        PersonModel.Person result = builder.build();
        // 结果: id=1, name="original", email="new@example.com"
         */
    }

    /**
     * 最佳实践 #5: 默认值处理
     *
     * proto3 默认值规则：
     * - 数值类型: 0
     * - 字符串: ""
     * - bytes: 空 ByteString
     * - bool: false
     * - enum: 第一个定义的值 (通常是 0)
     * - message: null/unset
     */
    public static class DefaultValuesExample {
        /*
        PersonModel.Person person = PersonModel.Person.newBuilder().build();

        // 这些都返回默认值
        int id = person.getId();           // 0
        String name = person.getName();    // ""
        String email = person.getEmail();  // ""

        // 检查字段是否被显式设置
        boolean hasId = person.hasId();           // proto3 中通常为 false（除非使用 optional）
         */
    }

    /**
     * 最佳实践 #6: 检查字段是否被设置（使用 optional）
     *
     * proto3 中可以使用 optional 关键字启用字段检查
     * message Person {
     *     optional int32 id = 1;
     *     optional string name = 2;
     * }
     */
    public static class OptionalFieldsExample {
        /*
        PersonModel.Person person = PersonModel.Person.newBuilder()
                .setId(1)
                .build();

        // 使用 optional 后可以检查是否被设置
        if (person.hasId()) {
            int id = person.getId();
        }

        if (person.hasName()) {
            String name = person.getName();
        }
         */
    }

    /**
     * 最佳实践 #7: 性能优化
     *
     * 1. 重用 Builder 对象
     * 2. 避免频繁的 build() 调用
     * 3. 使用 CodedInputStream/CodedOutputStream 处理流
     * 4. 对于大消息，考虑分割处理
     */
    public static class PerformanceOptimizationExample {
        /*
        // ✅ 好的做法：重用 Builder
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();

        for (int i = 0; i < 1000; i++) {
            builder.setId(i)
                   .setName("user" + i)
                   .clearEmail();  // 清空不需要的字段

            PersonModel.Person person = builder.build();
            // 处理 person
        }

        // ❌ 不好的做法：频繁创建 Builder
        for (int i = 0; i < 1000; i++) {
            PersonModel.Person person = PersonModel.Person.newBuilder()
                    .setId(i)
                    .setName("user" + i)
                    .build();
            // 处理 person
        }
         */
    }

    /**
     * 最佳实践 #8: 处理未知字段和扩展性
     *
     * proto3 对于未知字段的处理方式与 proto2 不同
     */
    public static class UnknownFieldsExample {
        /*
        // 在 proto3 中，未知字段会被丢弃（这是默认行为）
        // 在 proto2 中，可以保留未知字段

        // 获取未知字段集合
        UnknownFieldSet unknownFields = person.getUnknownFields();
         */
    }

    /**
     * 最佳实践 #9: 异常处理
     */
    public static class ExceptionHandlingExample {
        /*
        byte[] data = new byte[]{1, 2, 3};

        try {
            PersonModel.Person person = PersonModel.Person.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            // 处理格式错误
            System.err.println("Invalid protobuf format: " + e.getMessage());
        } catch (Exception e) {
            // 其他异常
            System.err.println("Unexpected error: " + e.getMessage());
        }
         */
    }

    /**
     * 最佳实践 #10: 项目结构建议
     *
     * 项目结构：
     * my-project/
     * ├── src/main/
     * │   ├── proto/                    ← .proto 文件存放处
     * │   │   ├── models/user.proto
     * │   │   ├── models/order.proto
     * │   │   └── ...
     * │   └── java/
     * │       └── com/example/
     * │           ├── model/            ← 业务模型/DTO
     * │           ├── service/
     * │           └── ...
     * ├── target/generated-sources/
     * │   └── protobuf/java/           ← 自动生成的 protobuf 代码
     * │       └── com/example/proto/
     * │           ├── UserProto.java
     * │           └── OrderProto.java
     * └── pom.xml
     *
     * 注意：
     * - .proto 文件存放在 src/main/proto/
     * - 生成的代码由 Maven 插件自动放在 target/generated-sources/
     * - IDE 通常会自动识别这个目录
     * - 不要手工编辑生成的代码
     * - 不要将生成的代码提交到版本控制
     */
    public static class ProjectStructureExample {
        /*
        项目配置 (pom.xml):

        <build>
            <plugins>
                <plugin>
                    <groupId>org.xolstice.maven.plugins</groupId>
                    <artifactId>protobuf-maven-plugin</artifactId>
                    <version>0.6.1</version>
                    <configuration>
                        <protocArtifact>
                            com.google.protobuf:protoc:3.19.0:exe:${os.detected.classifier}
                        </protocArtifact>
                    </configuration>
                    <executions>
                        <execution>
                            <goals>
                                <goal>compile</goal>
                                <goal>test-compile</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
         */
    }

    /**
     * 最佳实践 #11: 版本控制最佳实践
     *
     * .gitignore 配置：
     * target/generated-sources/  ← 生成的代码不需要提交
     * *.class
     * .idea/
     * *.iml
     *
     * 应该提交：
     * ✅ .proto 文件
     * ✅ pom.xml
     * ✅ 业务代码
     *
     * 不应该提交：
     * ❌ 生成的 Java 代码
     * ❌ 编译结果
     */
    public static class VersionControlExample {
        // 参考上面的注释
    }

    /**
     * 最佳实践 #12: 向后兼容性
     *
     * Proto3 的兼容性规则：
     * 1. 不要更改字段编号
     * 2. 可以删除字段（但字段编号不能重用）
     * 3. 可以添加新字段（旧代码会忽略新字段）
     * 4. 不要改变字段类型
     *
     * 例如：
     * // v1
     * message Person {
     *     int32 id = 1;
     *     string name = 2;
     * }
     *
     * // v2 - 兼容 (添加新字段)
     * message Person {
     *     int32 id = 1;
     *     string name = 2;
     *     string email = 3;  // ✅ OK
     * }
     *
     * // v3 - 不兼容 (改变字段类型)
     * message Person {
     *     int32 id = 1;
     *     string name = 2;
     *     int32 email = 3;   // ❌ 会导致反序列化失败
     * }
     */
    public static class BackwardCompatibilityExample {
        // 参考上面的注释
    }

    /**
     * 最佳实践 #13: 与 gRPC 的集成
     *
     * 如果使用 gRPC，需要额外的插件配置：
     *
     * pom.xml:
     * <plugin>
     *     <groupId>org.xolstice.maven.plugins</groupId>
     *     <artifactId>protobuf-maven-plugin</artifactId>
     *     <version>0.6.1</version>
     *     <configuration>
     *         <protocArtifact>
     *             com.google.protobuf:protoc:3.19.0:exe:${os.detected.classifier}
     *         </protocArtifact>
     *         <pluginId>grpc-java</pluginId>
     *         <pluginArtifact>
     *             io.grpc:protoc-gen-grpc-java:1.41.0:exe:${os.detected.classifier}
     *         </pluginArtifact>
     *     </configuration>
     *     <executions>
     *         <execution>
     *             <goals>
     *                 <goal>compile</goal>
     *                 <goal>compile-custom</goal>
     *             </goals>
     *         </execution>
     *     </executions>
     * </plugin>
     */
    public static class GRPCIntegrationExample {
        // 参考上面的注释
    }

    /**
     * 总结：
     *
     * ✅ DO:
     * - 使用 Builder 模式创建消息
     * - 让 Maven 插件自动生成代码
     * - 在版本控制中只提交 .proto 文件
     * - 使用 toByteArray() 和 parseFrom() 进行序列化/反序列化
     * - 遵循向后兼容性规则
     * - 重用 Builder 对象以提高性能
     * - 使用 optional 关键字检查字段
     * - 处理异常情况
     *
     * ❌ DON'T:
     * - 手工编辑生成的代码
     * - 将生成的代码提交到版本控制
     * - 改变已发布的字段编号
     * - 改变字段类型
     * - 忽略异常处理
     * - 频繁创建新的 Builder 对象
     */
}

