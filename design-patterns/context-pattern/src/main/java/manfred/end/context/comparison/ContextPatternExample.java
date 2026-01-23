package manfred.end.context.comparison;

/**
 * Context 模式示例 - 用于对比
 *
 * Context 模式的特点：
 * 1. 追踪当前处理位置和层级关系
 * 2. 维护父子上下文链
 * 3. 提供位置信息用于验证和调试
 * 4. 不改变对象行为，只记录状态
 *
 * @author manfred
 */
public class ContextPatternExample {

    // ========== Context 抽象类 ==========
    static abstract class JsonContext {
        public static final int TYPE_ROOT = 0;
        public static final int TYPE_ARRAY = 1;
        public static final int TYPE_OBJECT = 2;

        protected int _type;
        protected int _index;

        public abstract JsonContext getParent();
        public abstract String getCurrentName();

        public boolean inArray() { return _type == TYPE_ARRAY; }
        public boolean inObject() { return _type == TYPE_OBJECT; }
        public boolean inRoot() { return _type == TYPE_ROOT; }

        public String getPath() {
            StringBuilder sb = new StringBuilder();
            buildPath(sb);
            return sb.toString();
        }

        private void buildPath(StringBuilder sb) {
            JsonContext parent = getParent();
            if (parent != null) {
                parent.buildPath(sb);
            }

            if (inRoot()) {
                sb.append("/");
            } else if (inArray()) {
                sb.append("[").append(_index).append("]");
            } else if (inObject()) {
                String name = getCurrentName();
                if (name != null) {
                    sb.append("/").append(name);
                }
            }
        }
    }

    // ========== 写入 Context 实现 ==========
    static class JsonWriteContext extends JsonContext {
        private final JsonWriteContext _parent;
        private String _currentName;
        private boolean _expectingValue; // 是否期待写入值

        private JsonWriteContext(int type, JsonWriteContext parent) {
            this._type = type;
            this._parent = parent;
            this._index = -1;
        }

        public static JsonWriteContext createRoot() {
            return new JsonWriteContext(TYPE_ROOT, null);
        }

        public JsonWriteContext createArrayContext() {
            return new JsonWriteContext(TYPE_ARRAY, this);
        }

        public JsonWriteContext createObjectContext() {
            return new JsonWriteContext(TYPE_OBJECT, this);
        }

        @Override
        public JsonWriteContext getParent() {
            return _parent;
        }

        @Override
        public String getCurrentName() {
            return _currentName;
        }

        // ========== 核心方法：验证写入操作是否合法 ==========

        public boolean writeFieldName(String name) {
            if (!inObject()) {
                System.out.println("  ❌ 错误：不能在 " + getTypeDesc() + " 中写入字段名");
                return false;
            }
            if (_expectingValue) {
                System.out.println("  ❌ 错误：期待写入值，不能写入字段名");
                return false;
            }
            _currentName = name;
            _expectingValue = true;
            System.out.println("  ✅ 写入字段名: \"" + name + "\" at " + getPath());
            return true;
        }

        public boolean writeValue(Object value) {
            if (inObject() && !_expectingValue) {
                System.out.println("  ❌ 错误：必须先写入字段名");
                return false;
            }
            _index++;
            _expectingValue = false;
            System.out.println("  ✅ 写入值: " + value + " at " + getPath());
            return true;
        }

        private String getTypeDesc() {
            if (inRoot()) return "ROOT";
            if (inArray()) return "ARRAY";
            if (inObject()) return "OBJECT";
            return "UNKNOWN";
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) {
        System.out.println("========== Context 模式示例 ==========");
        System.out.println("特点：追踪位置、验证结构，不改变对象行为\n");

        // 构建 JSON: {"user": {"name": "Alice", "orders": [1, 2, 3]}}

        JsonWriteContext root = JsonWriteContext.createRoot();
        System.out.println("1. 创建根上下文");

        // 开始写对象
        JsonWriteContext objCtx = root.createObjectContext();
        System.out.println("\n2. 进入对象上下文");

        // 写 "user" 字段
        objCtx.writeFieldName("user");

        // "user" 的值是一个对象
        JsonWriteContext userCtx = objCtx.createObjectContext();
        System.out.println("\n3. 进入 user 对象上下文");

        // 写 "name" 字段
        userCtx.writeFieldName("name");
        userCtx.writeValue("Alice");

        // 写 "orders" 字段
        userCtx.writeFieldName("orders");

        // "orders" 的值是一个数组
        JsonWriteContext arrayCtx = userCtx.createArrayContext();
        System.out.println("\n4. 进入 orders 数组上下文");

        // 写数组元素
        arrayCtx.writeValue(1);
        arrayCtx.writeValue(2);
        arrayCtx.writeValue(3);

        // 尝试错误操作
        System.out.println("\n5. 尝试错误操作：");
        arrayCtx.writeFieldName("invalid");  // 错误：数组中不能写字段名

        JsonWriteContext obj2Ctx = arrayCtx.createObjectContext();
        obj2Ctx.writeValue("value");  // 错误：对象中必须先写字段名

        System.out.println("\n========== 对比总结 ==========");
        System.out.println("状态模式：对象行为随状态改变（订单：待支付 -> 待发货 -> 已发货）");
        System.out.println("Context模式：追踪位置和层级，验证操作合法性（JSON：/user/orders[2]）");
    }
}

